package org.ssm;

import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Mybatis生成工具
 *
 * @author chenly1
 * @create 2019-09-01 20:12
 */
public class MysqlGenerator {

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		String help = "请输入" + tip + "：";
		System.out.println(help);
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	/**
	 * RUN THIS
	 */
	public static void main(String[] args) {

		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		//需要修改的地方
		gc.setAuthor("chenly1");
		gc.setBaseResultMap(true);
		gc.setBaseColumnList(true);
		gc.setOpen(false);
		//是否覆盖已有的文件
		//gc.setFileOverride(false);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		Props props = new Props("jdbc.properties");
		dsc.setUrl(props.getStr("jdbcUrl"));
		dsc.setDriverName("jdbcDriver");
		dsc.setUsername(props.getStr("username"));
		dsc.setPassword(props.getStr("password"));
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		 String moduleName = scanner("模块名");
		pc.setModuleName(moduleName);
		pc.setEntity("model");
		pc.setParent("org.ssm");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg =  new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = Maps.newHashMap();
				map.put("mapper", "org.ssm.mapper." + moduleName);
				map.put("model", "org.ssm.model." + moduleName);
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = generateFileOutConfigs(projectPath, pc);

		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null)
				.setController(null)
				.setService(null)
				.setServiceImpl(null)
				.setMapper(null)
				.setEntity(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		strategy.setInclude(scanner("表名"));
		strategy.setEntityTableFieldAnnotationEnable(true);
		mpg.setStrategy(strategy);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

	private static List<FileOutConfig> generateFileOutConfigs(final String projectPath, final PackageConfig pc) {
		List<FileOutConfig> focList = Lists.newArrayList();
		focList.add(new FileOutConfig("/template/CustomMapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/resources/mybatis/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});

		focList.add(new FileOutConfig("/template/CustomMapper.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/java/com/cnc/si/mapper/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" +
						StringPool.DOT_JAVA;
			}
		});

		focList.add(new FileOutConfig("/template/CustomEntity.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/java/com/cnc/si/model/" + pc.getModuleName() + "/" + tableInfo.getEntityName() +
						StringPool.DOT_JAVA;
			}
		});
		return focList;
	}

}
