package com.sciov.lh.mybatis;

import static org.mybatis.generator.internal.util.JavaBeansUtil.getGetterMethodName;
import static org.mybatis.generator.internal.util.JavaBeansUtil.getSetterMethodName;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.config.Context;

public class VoClassGenerator extends AbstractJavaGenerator {

	
	Properties properties;
	
	
	public VoClassGenerator(Properties properties) {
		super();
		this.properties = properties;
	}

	
	@Override
	public List<CompilationUnit> getCompilationUnits() {
		
		FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
		progressCallback.startTask(getString("Progress.6", table.toString())); //$NON-NLS-1$
		CommentGenerator commentGenerator = context.getCommentGenerator();

		String fullQualif = PluginUtil.getTargetPackage(properties) + "." + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Vo";
		

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(fullQualif);

		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(topLevelClass);

		topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");

		List<IntrospectedColumn> introspectedColumns;
		if (includePrimaryKeyColumns()) {
			if (includeBLOBColumns()) {
				introspectedColumns = introspectedTable.getAllColumns();
			} else {
				introspectedColumns = introspectedTable.getNonBLOBColumns();
			}
		} else {
			if (includeBLOBColumns()) {
				introspectedColumns = introspectedTable.getNonPrimaryKeyColumns();
			} else {
				introspectedColumns = introspectedTable.getBaseColumns();
			}
		}

		String rootClass = getRootClass();
		for (IntrospectedColumn introspectedColumn : introspectedColumns) {
			if (RootClassInfo.getInstance(rootClass, warnings).containsProperty(introspectedColumn)) {
				continue;
			}

			Field field = getJavaBeansField(introspectedColumn);
			field.addAnnotation("@ApiModelProperty(\"" + introspectedColumn.getRemarks() + "\")");
			topLevelClass.addField(field);
			topLevelClass.addImportedType(field.getType());

			Method method = getJavaBeansGetter(introspectedColumn);
			topLevelClass.addMethod(method);

			method = getJavaBeansSetter(introspectedColumn);
			topLevelClass.addMethod(method);
		}

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (context.getPlugins().modelBaseRecordClassGenerated(topLevelClass, introspectedTable)) {
			answer.add(topLevelClass);
		}
		return answer;
	}

	private boolean includePrimaryKeyColumns() {
		return !introspectedTable.getRules().generatePrimaryKeyClass() && introspectedTable.hasPrimaryKeyColumns();
	}

	private boolean includeBLOBColumns() {
		return !introspectedTable.getRules().generateRecordWithBLOBsClass() && introspectedTable.hasBLOBColumns();
	}
	
	public Field getJavaBeansField(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType fqjt = introspectedColumn
                .getFullyQualifiedJavaType();
        String property = introspectedColumn.getJavaProperty();

        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        
        if(fqjt.equals(FullyQualifiedJavaType.getDateInstance())) {
        	field.setType(FullyQualifiedJavaType.getStringInstance());
        }else {
        	field.setType(fqjt);
        }
        
        
        field.setName(property);
        context.getCommentGenerator().addFieldComment(field,
                introspectedTable, introspectedColumn);

        return field;
    }
	
	
	 public Method getJavaBeansSetter(IntrospectedColumn introspectedColumn) {
	        FullyQualifiedJavaType fqjt = introspectedColumn
	                .getFullyQualifiedJavaType();
	        
	        if(fqjt.equals(FullyQualifiedJavaType.getDateInstance())) {
	        	fqjt = FullyQualifiedJavaType.getStringInstance();
	        }
	        
	        String property = introspectedColumn.getJavaProperty();

	        Method method = new Method();
	        method.setVisibility(JavaVisibility.PUBLIC);
	        method.setName(getSetterMethodName(property));
	        method.addParameter(new Parameter(fqjt, property));
	        context.getCommentGenerator().addSetterComment(method,
	                introspectedTable, introspectedColumn);

	        StringBuilder sb = new StringBuilder();
	        if (isTrimStringsEnabled() && introspectedColumn.isStringColumn()) {
	            sb.append("this."); //$NON-NLS-1$
	            sb.append(property);
	            sb.append(" = "); //$NON-NLS-1$
	            sb.append(property);
	            sb.append(" == null ? null : "); //$NON-NLS-1$
	            sb.append(property);
	            sb.append(".trim();"); //$NON-NLS-1$
	            method.addBodyLine(sb.toString());
	        } else {
	            sb.append("this."); //$NON-NLS-1$
	            sb.append(property);
	            sb.append(" = "); //$NON-NLS-1$
	            sb.append(property);
	            sb.append(';');
	            method.addBodyLine(sb.toString());
	        }

	        return method;
	    }

    public Method getJavaBeansGetter(IntrospectedColumn introspectedColumn) {
        FullyQualifiedJavaType fqjt = introspectedColumn
                .getFullyQualifiedJavaType();
        if(fqjt.equals(FullyQualifiedJavaType.getDateInstance())) {
        	fqjt = FullyQualifiedJavaType.getStringInstance();
        }
        String property = introspectedColumn.getJavaProperty();

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(fqjt);
        method.setName(getGetterMethodName(property, fqjt));
        context.getCommentGenerator().addGetterComment(method,
                introspectedTable, introspectedColumn);

        StringBuilder sb = new StringBuilder();
        sb.append("return "); //$NON-NLS-1$
        sb.append(property);
        sb.append(';');
        method.addBodyLine(sb.toString());

        return method;
    }
}
