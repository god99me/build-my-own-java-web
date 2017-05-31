# build-my-own-java-web
《从零开始写 Java Web》 框架

## build development environment
### maven
配置 maven 源

maven 仓库
http://mvnrepository.com/

apache commons
commons
db utils
collections
部分工作集中在对 apache 的二次封装上

## chapter 1
1. 利用IDEA 创建 Maven 项目
2. 项目目录结构
3. 将 Maven 项目转化为 Web 项目
4. servlet 3.0 可以做到 web.xml 的零配置
5. 注解 WebServlet 配置请求路径
6. IDEA control + N 自动生成提示
7. req.setAttribute 设置 servlet - jsp hook
8. IDEA 可以在 DEBUG 模式下运行，实现类的*热部署*和*断点调试*，
但社区版可能需要特别的配置。热部署只需要手工编译即可，但有些情况下无法进行热部署，比如修改了类名、方法名、成员变量名等
9. req.getRequestDispatcher 配置返回页面的路径
10. 在 IDEA 中配置 Tomcat
11. 在 Maven 面板中可以双击运行命令
12. 在 IDEA 中集成 Git
    git remote add origin remote_repo_address
    git push -u origin master

## chapter 2
1. MVC 架构组织 Web 应用项目结构
2. 标准的 MVC 架构中没有服务层和 DAO 层，它们作为衔接控制器层与数据库之间的纽带。
3. 关系型数据库中的表结构对应到 java 中是一个 map
4. 推荐将 JSP 放到 WEB-INF 内部，这样用户无法通过浏览器直接访问地址栏请求放在内部的 JSP，必须通过 Servlet 程序进行转发或重定向
5. JDBC 获取的结果集通过 Bean 的 setter 依次封装到 POJO 中
6. 在 service 类中读取配置文件，或者连接 JDBC 都是很不合理的，更多其他的 service 都要做同样的事情，它们是公共的代码
7. 编写了 DatabaseHelper
8. 最简单的 DBHelper 只包含了加载配置文件，及获得连接和关闭数据库连接两个方法。
9. Apache Common 有 DbUtils 的类库，提供了对 JDBC 的封装，DbUtils 首先执行 SQL 语句并返回一个结果集(ResultSet)，然后通过反射去创建
并初始化实体对象 （eg. BeanListHandler(entity.class)）
10. 频繁的获取和关闭数据库连接开销很大，使用 ThreadLocal 可以存放线程本地变量，保证一个线程中只有一个数据库连接。
11. 加入数据库连接池 DBCP 防止频繁获取和关闭数据库连接。保证 BasicDataSource 是静态的，删除 closeConnection 方法。
12. 测试数据库与开发数据库相分离，单元测试中执行SQL脚本重置测试用数据库。从当前线程中获取线程上下文中的 ClassLoader？
循环读取 sql 脚本文件并执行 executeUpdate 语句。没必要在所有单元测试中都编写上述相同的代码，因此依然整合到 DatabaseHelper 中
13. 整个 web 应用只需要唯一的 Service 实例，将 service 实例的创建放入 init() 方法中可以在一定程度上避免多个实例的创建。但是更好的办法
是使用单例模式／反射
14. 将同一个业务模块的 servlet 合并到一个类中去，每个方法都处理一种特定的请求。返回有两种形式，一种是返回视图(view)，另一种是返回 json

## chapter 3
1. 利用 @Controller 来定义 Controller 类，通过 @Inject 来注入 Service，@Action 注解所定义的方法，在这些方法中，通过调用 Service 的
成员方法来完成具体的业务逻辑。最终返回 json 或是 jsp。并且当 Controller 替代 Servlet 后，需要框架来执行 doGet 和 doPost 方法。
2. SLF4J 是日志框架的接口， Log4J 是日志的一种具体实现
3. 返回 JSON 数据需要 Jackson 来进行 json 的序列化
4. JDBC 的轻量级类库 Apache Commons DbUtils，以及数据库连接池 DBCP
5. 需要配置的基本项主要有 JDBC 相关的配置、`base_package` 项目的基础包名、`jsp_path` jsp 的基础路径、`asset_path` 静态资源文件的
基础路径，如 JS\CSS\图片等。静态资源文件只需要直接返回即可。
6. 创建一个常量类（接口）来维护配置文件中相关的配置项名称如 `ConfigConstant`
7. 关于配置项的问题还涉及到不同位置的配置优先级不同的问题
8. 开发一个类加载器来处理注解，类加载器加载类 `Class.forName` 需要提供类名与是否初始化的标志，这里的初始化是指**是否执行类的静态代码块**。
9. 获取指定包名下的所有类是自定义类加载器中最复杂的方法，主要完成**根据包名并将其转换为文件路径（／ -> .）**的工作。获取到的文件可能是 class
文件或 jar 包。URL 类用来分析（getProtocol）属于文件或者 jar 包。
10. 自己开发的框架的目标是在控制器类上使用 @Controller；在控制类的方法上使用 @Action;在服务类上使用 @Service；在控制器类中使用 @Inject
将 Service 注入进来。
11. 开发了一个辅助类用来加载基础包名下的所有 Controller 类或 Service 类，目前还不知道有什么用
12. 封装反射类的作用有 1. 增加日志系统 2. 先将方法设置为 `setAccessible(true)` 再 `method.invoke` 调用方法。（强制调用 private 方法）
 3. 先将属性设置为 `field.setAccessible(true)` 再 `field.set` 设置（注入）成员变量的值
13. **所有**被框架管理的类，都是通过先获得类名，再反射创建实例并缓存在 Map 中，随时获取 Map 中的类 `BeanHelper`
14. IOC 容器实现依赖注入功能是首先通过 BeanHelper 获取所有 Bean 的 Map（映射），然后遍历这个映射，通过反射获取类中所有的成员变量，并在
带有 @Inject 注解的成员变量处 `setField` 方法来修改该成员变量的值。由于 BeanHelper 中的 Bean Map 是单例的，所以在 IOC 容器中所管理的
对象都是单例的。
15. 所谓的 ControllerHelper 类(DispatcherServlet) 获取所有定义了 @Controller 注解的类，通过反射获取该类中所有带有 @Action 注解的方法，
获取注解中的请求表达式，进而**获取请求方法与请求路径**，封装一个请求对象(Request)和处理对象(Handler)（单例？），并将它们建立一个映射关系，并提供一个
可以根据请求方法与请求路径获取处理对象的方法。
16. apache.commons.lang3.builder.EqualsBuilder apache.commons.lang3.builder.HashCodeBuilder 可用来重写 hashCode & equals 方法
17. Handler 对象中有两个成员变量，分别持有所对应的 Class 与所对应的 Action method
18. 请求转发器的工作原理，从 HttpServletRequest 对象中获取请求方法与请求路径， 通过 ControllerHelper 获取对应的 Handler 对象，继而获取
Controller 类，并通过 BeanHelper 获取实例对象。随后可以从 HttpServletRequest 对象中获取所有请求参数，初始化到一个类 Param 的对象中，该对象
将所有参数保存到 Map 结构中。
19. 还可以**从 Handler 对象中获取 Action 方法的返回值**，如果是 View 类型的视图对象（需和 Model 数据结合）则返回 JSP 对象。如果是 Data
类型的对象，则返回 Model ，该 model 会被框架写入 HttpServletResponse 对象中，最后带回浏览器。
20. DispatcherServlet 继承了 HttpServlet，在初始化方法中注册处理 JSP 的 servlet，添加到 app_jsp_path 的映射；注册处理静态资源的默认
servlet，添加 app_asset_path 的映射；重写了 service 方法，用自己的逻辑代替 doGet doPost 调用


