package com.atguigu.springboot.dao;

import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;//装配一个数据库组件
	
	static{//添加初始数据
		employees = new HashMap<Integer, Employee>();

		employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "D-AA")));
		employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1, new Department(102, "D-BB")));
		employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0, new Department(103, "D-CC")));
		employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104, "D-DD")));
		employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105, "D-EE")));
	}
	
	private static Integer initId = 1006;

	//下面的employee是SpringMVC传进来的参数自动绑定的
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}//自动设置id
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));//从数据库拿数据给employee相关的字段赋值
		employees.put(employee.getId(), employee);//employees用于在页面展示
	}
	
	public Collection<Employee> getAll(){
		return employees.values();
	}//展示所有的员工信息
	
	public Employee get(Integer id){
		return employees.get(id);
	}
	
	public void delete(Integer id){
		employees.remove(id);
	}
}
