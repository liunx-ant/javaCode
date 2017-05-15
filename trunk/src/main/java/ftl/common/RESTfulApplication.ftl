package ${apiPackageNamePrefix};

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/${applicationName}Service/1.0.0")
public class RESTfulApplication extends Application {
	 
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		try {
			<#list serviceImplNames as serviceImplName>
				resources.add(Class.forName("${servicePath}${serviceImplName}"));
			</#list>
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resources;
	}
	
}
