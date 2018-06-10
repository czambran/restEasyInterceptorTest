import com.arquillian.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Arquillian.class)
public class RestEasyInterceptorsIT {

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive archive = null;

        try {

            File[] files = Maven.resolver()
                    .loadPomFromFile("pom.xml")
                    .importRuntimeDependencies()
                    .resolve()
                    .withTransitivity()
                    .asFile();

            archive = ShrinkWrap.create(WebArchive.class, "sample.war")
                    .addClasses(RestEndpoint.class, RestEndpointInterface.class, StartUp.class)
                    .addAsLibraries(files)
                    .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                    .addAsWebInfResource("web.xml", "web.xml")
                    .addAsWebInfResource("jboss-web.xml")
                    .addAsWebInfResource("jboss-deployment-structure.xml");

            System.out.println(archive.toString(true));

            archive.as(ZipExporter.class).exportTo(new File("/tmp/" + archive.getName()), true);

        } catch (Exception e) {
            System.out.println("Couldn't create arquilian deployement because of the following error: " + e.getMessage());
            e.printStackTrace();
        }
        return archive;
    }

    private RestEndpointInterface restEasyClient = ProxyFactory.create(RestEndpointInterface.class, "http://localhost:8080/arquillian");

    @Test
    public void restEndpointWorks() throws Exception {
        Assert.assertEquals("Hello World", restEasyClient.helloWorld());
    }

    @Test
    public void restEndpointWorks2() throws Exception {
        Assert.assertEquals("token", restEasyClient.getAuthorizationHeaderReceived());
    }

}
