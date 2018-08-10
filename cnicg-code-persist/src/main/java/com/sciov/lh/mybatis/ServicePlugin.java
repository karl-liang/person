package com.sciov.lh.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

public class ServicePlugin extends PluginAdapter {

	AbstractJavaGenerator serviceInterfaceGenerator  = null;
	
	AbstractJavaGenerator serviceImplGenerator  = null;
	
	AbstractJavaGenerator controllerGenerator  = null;
	
	@Override
	public boolean validate(List<String> warnings) {
		
		return Boolean.parseBoolean(properties.getProperty("enable","true"));
	}
	
	private void initGenerator(IntrospectedTable introspectedTable) {
		if(serviceInterfaceGenerator == null) {
			serviceInterfaceGenerator = new ServiceInterfaceGenerator(this.properties);
		}
			
		serviceInterfaceGenerator.setContext(context);
		serviceInterfaceGenerator.setIntrospectedTable(introspectedTable);
		serviceInterfaceGenerator.setProgressCallback(new VerboseProgressCallback());
		
		
		if(serviceImplGenerator == null) {
			serviceImplGenerator = new ServiceImplGenerator(this.properties);
		}
			
		serviceImplGenerator.setContext(context);
		serviceImplGenerator.setIntrospectedTable(introspectedTable);
		serviceImplGenerator.setProgressCallback(new VerboseProgressCallback());
		
		

		if(controllerGenerator == null) {
			controllerGenerator = new ControllerGenerator(this.properties);
		}
			
		controllerGenerator.setContext(context);
		controllerGenerator.setIntrospectedTable(introspectedTable);
		controllerGenerator.setProgressCallback(new VerboseProgressCallback());
	}
	
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		
		initGenerator(introspectedTable);
		
	
		List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

		List<CompilationUnit> compilationUnits = serviceInterfaceGenerator.getCompilationUnits();
		for (CompilationUnit compilationUnit : compilationUnits) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
					PluginUtil.getTargetProject(properties));
			answer.add(gjf);
		}
		
		compilationUnits = serviceImplGenerator.getCompilationUnits();
		for (CompilationUnit compilationUnit : compilationUnits) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
					PluginUtil.getTargetProject(properties));
			answer.add(gjf);
		}
		
		compilationUnits = controllerGenerator.getCompilationUnits();
		for (CompilationUnit compilationUnit : compilationUnits) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
					PluginUtil.getControllerTargetProject(properties));
			answer.add(gjf);
		}
		return answer;

	}

}
