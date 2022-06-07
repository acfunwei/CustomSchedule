# 工程简介
该项目是自定义的一个定时任务,该任务支持使用corn表达式或者TimeUnit两种不同的触发方式


# 延伸阅读
<h3>使用步骤：</h3>

######     1、将该项目构建下载并引入项目中添加maven依赖

`        <dependency>`<br>
`        <groupId>com.example</groupId>`<br>
`        <artifactId>shedule</artifactId>`<br>
`        <version>0.0.1-SNAPSHOT</version>`<br>
`        </dependency>`<br>

###### 2、在启动类上添加注解`@EnableCustomSchedule`

###### 3、执行sql/sqlFile内部的SQL，也可以使用自定义的类重写`CustomScheduleBean.getInstance`方法。

###### 4、自定义添加任务执行锁，实现`CustomLock`里面的方法，并注册到容器内部。

###### 5、自定义添加任务执行日志，实现`ScheduleLogger`里面的方法，并注册到容器内部。

###### 6、自定义添加任务,默认是采用FbpSysJobs内部的jobType字段，该字段为实体注册到容器内部的名称。