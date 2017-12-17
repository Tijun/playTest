package base;

import akka.stream.Materializer;
import org.junit.After;
import play.Application;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import static play.test.Helpers.*;

/**
 * play 上下文
 */
public class PlayApplicationContext {

    protected Application app;

    protected Materializer mat;

    static {
        //当application.conf 配置文件不在默认conf/application.conf 目录时需配置该系统变量
        System.setProperty("-Dconfig.resource","application.conf");
        //暂且实现对guice注册的类测试，当routes不在默认的conf/routes 目录时无法测试routes 待解决方案

    }

   protected PlayApplicationContext(){
        startPlay();
        bindingInject(); //为测试类 PlayApplicationContext子类的@Inject 接口注入实例
   }

    /**
     * 为测试类 PlayApplicationContext子类的 @Inject修饰属性接口注入实例
     */
    private void bindingInject(){
        final Field [] fields = this.getClass().getDeclaredFields();
        Optional.ofNullable(fields).ifPresent(fieldsList -> {
            Arrays.asList(fieldsList).stream().filter(field->{ // 找出所有被@Inject 修饰的field
                Inject inject =  field.getAnnotation(Inject.class);
                return inject != null;
            }).forEach(field->{
                field.setAccessible(true);// 对field 设置private 可访问权限
                try {
                    startPlay();
                    field.set(this,app.injector().instanceOf(field.getType()));//使用guice注入实例
                } catch (IllegalAccessException e) {
                    System.out.println("Inject fail " + e.getMessage());
                }
            });
        });

    }

    /**
     * 如果需要改变application配置时该方法可以被重写
     */
    protected void startPlay() {
        app = fakeApplication();
        start(app);
        mat = app.getWrappedApplication().materializer();
    }

    /**
     * 测试结束后停止play
     */
    @After
    public void stopPlay() {
        if (app != null) {
            stop(app);
            app = null;
        }
    }

}
