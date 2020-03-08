-- MySQL dump 10.13  Distrib 5.6.43, for Win64 (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	5.6.43-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `blog`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (65),(65),(65),(65),(65);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_blog`
--

DROP TABLE IF EXISTS `t_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `recommend` bit(1) NOT NULL,
  `tag_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK292449gwg5yf7ocdlmswv9w4j` (`type_id`),
  KEY `FK8ky5rrsxh01nkhctmo7d48p82` (`user_id`),
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`),
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_blog`
--

LOCK TABLES `t_blog` WRITE;
/*!40000 ALTER TABLE `t_blog` DISABLE KEYS */;
INSERT INTO `t_blog` VALUES (26,'','\0','aaaaadf','2019-12-19 17:35:10','https://picsum.photos/id/1011/800/400','','','','adf','2019-12-19 17:35:25',0,3,1,'adsv','','5,6'),(27,'','\0','This is blog1','2019-12-20 08:56:11','https://picsum.photos/id/1011/800/400','','','','This is blog1','2019-12-20 08:56:11',0,1,1,'This is blog1','','5'),(28,'','','This is blog12','2019-12-20 08:56:30','https://picsum.photos/id/1011/800/400','转载','','\0','This is blog12','2020-01-18 13:44:33',0,3,1,'This is blog1This is blog1','\0','6,7'),(29,'\0','','This is blog13','2019-12-20 08:56:51','https://picsum.photos/id/1011/800/400','原创','','','This is blog123','2020-01-18 13:44:20',0,4,1,'This is blog1This is blog1This is blog1This is blog1This is blog1','','5,6,7'),(40,'\0','','# Class类的三种实例化模式  \r\n### 类的定义\r\n`public final class Class<T>extends Object implements Serializable,GenericDeclaration,Type,AnnotatedElement`  \r\n### 第一种 Object类支持：public final Class<?> getClass()  \r\n```java\r\nPerson per = new Person();\r\nClass<? extends Person > cls = per.getClass();\r\nSystem.out.println(cls);# class 包.类\r\nSystem.out.println(cls).getName();# 包.类\r\n```  \r\n如果只是想获得Class类对象必须得先获得指定类的对象\r\n\r\n### JVM支持  \r\n```java\r\nClass<? extends Person > cls = Person.Class;\r\nSystem.out.println(cls);# class 包.类\r\nSystem.out.println(cls).getName();# 包.类\r\n```  \r\n  \r\n### Class类支持  \r\n```java\r\n#Person包没有导入\r\nClass<? > cls = Class.forName(\"包.类\");\r\nSystem.out.println(cls);# class 包.类\r\nSystem.out.println(cls).getName();# 包.类\r\n```  \r\n\r\n### 工厂设计模式升级\r\n```java\r\npackage com.Runtim;\r\n\r\nimport java.lang.reflect.InvocationTargetException;\r\n\r\npublic class TestRun {\r\n    public static void main(String[] args) {\r\n        Houseservice h= new Houseservice();\r\n        Class<? extends Houseservice> cls= h.getClass();\r\n        System.out.println(cls);\r\n        System.out.println(\"******************\");\r\n        try {\r\n            Cloudservice c =Factory.get(\"com.Runtim.Cloudservice\",Cloudservice.class);\r\n            c.send();\r\n        } catch (ClassNotFoundException e) {\r\n            e.printStackTrace();\r\n        } catch (NoSuchMethodException e) {\r\n            e.printStackTrace();\r\n        } catch (IllegalAccessException e) {\r\n            e.printStackTrace();\r\n        } catch (InvocationTargetException e) {\r\n            e.printStackTrace();\r\n        } catch (InstantiationException e) {\r\n            e.printStackTrace();\r\n        }\r\n    }\r\n\r\n}\r\ninterface service{\r\n    public void send();\r\n}\r\nclass Houseservice implements service{\r\n    public Houseservice(){\r\n        System.out.println(this+\"构造方法\");\r\n    }\r\n    @Override\r\n    public void send() {\r\n        System.out.println(\"House send\");\r\n    }\r\n}\r\nclass Cloudservice implements service{\r\n    public Cloudservice(){\r\n        System.out.println(this+\"构造方法\");\r\n    }\r\n    @Override\r\n    public void send() {\r\n        System.out.println(\"Cloud send\");\r\n    }\r\n}\r\nclass Factory{\r\n    public static <T> T get(String stringname,Class<T> clazz) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {\r\n        T instance=null;\r\n        instance =(T)Class.forName(stringname).getDeclaredConstructor().newInstance();\r\n        return instance;\r\n    }\r\n}\r\n```  \r\n可以指定任意类进行返回实例，而不修改工厂类','2019-12-22 15:41:24','https://picsum.photos/id/1011/800/400','原创','','\0','tag','2020-01-18 13:44:04',0,3,1,'asd','','5'),(41,'\0','','# sql语句  \r\n\r\n## DDL  \r\n\r\n用来定义数据库对象：库、表、列  \r\n创建、删除、修改 结构\r\n\r\n* 数据库  \r\n\r\n  1. 查看所有数据库：`SHOW DATABASES`\r\n  2. 切换（要操作的）数据库：`USE 数据库名`\r\n  3. 创建数据库:`CREATE DATABASES [IF NOT EXISTS] mydb [CHARSET=utf8]`\r\n  4. 删除数据库：`DROP DATABASES [IF EXISTS] mydb`\r\n  5. 修改数据库编码:`ALTER DATABASES mydb CHARACTER SET utf8`  \r\n\r\n* 数据类型  \r\n\r\n  *  文本类\r\n     1. CHAR(size)\r\n     2. VARCHAR(size)  大于size255转化为TEXT\r\n     3. TINYTEXT\r\n     4. TEXT 65535个字符\r\n     5. BLOB 65535个字节\r\n     6. MEDIUMTEXT 16 777 215个字符\r\n     7. LONGTEXT 4 294 967 295个字符\r\n     8. ENUM(x,y,z) 只能输入预定好的值65535\r\n     9. SET 与MEUM类似，最多包含64个列表项，可存储一个以上的值\r\n  *  数据类\r\n     1. TINYINT(size) -128 to 127      ro 0 to 255\r\n     2. SMALLINT(size) -32768 to 32767\r\n     3. MEDIUMINT(size) -8388608 to 8388607\r\n     4. INT(size)    -2147483648 to 2147483647\r\n     5. BIGINT(size)  -9223372036854775808 to 9223372036854775807\r\n     6. FLOAT(size,d) d规定小数点右侧的最大位数\r\n     7. DOUBLE(size,d) \r\n     8. DECIMAL  字符串存储的DOUBLE类型，允许固定的小数点\r\n  *  时间类\r\n     1. DATE()  YYYY-MM-DD\r\n     2. DATETIME()  YYYY-MM-DD HH:MM:SS\r\n     3. TIMESTAMP() YYYY-MM-DD HH:MM:SS使用Unix纪元‘1970-01-01 00:00:00’至今的描述存储\r\n     4. TIME()  HH:MM:SS\r\n     5. YEAR()   1901-2155   70-69\r\n\r\n* 表\r\n\r\n  1. 创建表：\r\n\r\n     ```\r\n     CREATE TABLE [IF NOT EXISTS] 表名(\r\n         列名 列类型,\r\n         列名 列类型, \r\n         列名 列类型,\r\n         列名 列类型\r\n     )[ charset = utf8];\r\n     ```\r\n\r\n  2. 查看当前数据库中所有表名称：SHOW TABLES;\r\n\r\n  3. 查看表结构： DESC 表名;\r\n\r\n  4. 删除表 DROP TABLE 表名;\r\n\r\n  5. 修改表 前缀：ALTER TABLE 表名  \r\n\r\n     * 添加：\r\n\r\n     ```\r\n     ALTER TABLE 表名 ADD(\r\n         列名 列类型,\r\n     ...\r\n     )\r\n     ```\r\n\r\n     * 修改列类型\r\n\r\n     ```sql\r\n     ALTER TABLE 表名 MODIFY 列名 列类型\r\n     ```\r\n\r\n     * 修改列名称\r\n\r\n     ```sql\r\n     ALTER TABLE 表名 CHANGE 原列名 新列名 列类型;\r\n     ```\r\n\r\n     * 删除列\r\n\r\n     ```sql\r\n     ALTER TABLE 表名 DROP 列名;\r\n     ```\r\n\r\n     * 修改表名\r\n\r\n     ```sql\r\n     ALTER TABLE 表名 RENAME 新表名\r\n     ```\r\n\r\n## DML\r\n\r\n数据操作语言  \r\n对表的记录进行更新（增删改）  \r\n\r\n* 插入数据  \r\n  `INSERT INTO 表名(列名，列名..)VALUES(\'\',\'\',..);`\r\n\r\n* 修改数据\r\n  `UPDATE  表名 SET 列名=值1，列名=值2，...[WHERE 条件]；`不加 where 该列全部修改\r\n\r\n* 删除数据\r\n  `DELETE FROM 表名 [WHERE 条件];`\r\n\r\n\r\n\r\n## DCL\r\n\r\n对用户的创建，授权\r\n\r\n* 创建用户\r\n  `CREATE USER 用户名@ip地址(\'%\') IDENTIFIED BY \'密码\';`\r\n* 给用户授权\r\n  `GRANT 权限1，权限n ON 数据库.* TO 用户名@ip地址`\r\n* 撤销授权\r\n  `REVOKE 权限1，权限n ON 数据库.* TO 用户名@ip地址`\r\n* 查看权限\r\n  `SHOW GRANTS FOR 用户名@ip地址`\r\n* 删除用户\r\n  `DROP USER 用户名@ip地址`\r\n\r\n## DQL\r\n\r\n* distinct  去重\r\n\r\n  `select distinct  col_name  from table_name`\r\n\r\n* order by   默认为asc\r\n\r\n  `select * from table_name [where] order by col_name [asc/desc][,col_name [asc/desc]...]`\r\n\r\n* limit(dialect)\r\n\r\n  `select * from table_name [where] [order by col_name] limit[offset,]rowCount `\r\n\r\n* insert into and select\r\n\r\n  ` insert into table1 [(col1,col2)]select col3,col4 from table2` \r\n\r\n* in 语法\r\n\r\n  `select * from table_name col_name in (value1,value2)`value 可用select col_name获得\r\n\r\n* between      （a<=b<=c）  not between\r\n\r\n* like\r\n\r\n  `select * from table_name where col_name [not] like pattern`\r\n\r\n  \"%\"可以匹配任何字符串\r\n\r\n* count\r\n\r\n  `select count(*) 自定义列名 from table_name` 用于查询该列有多少条值，为Null不计\r\n\r\n* sum\r\n\r\n  `select sum(slary) from table_name `  例 计算工资（该列值）总和  null为0\r\n\r\n* max、min、avg\r\n\r\n* **group by**   \r\n\r\n  `select job,count(*) from emp [where] group by job`与之前的聚合函数配合使用，每一份组的聚合值\r\n\r\n* **having**\r\n\r\n  `select job,count(*) from emp [where] group by job having count(*)>=2`\r\n\r\n### 连接查询\r\n\r\n* 内连接\r\n\r\n  `select * from table_1,table_2 ` 笛卡尔积\r\n\r\n  `inner join` 加条件   `nature join`自动寻找条件\r\n\r\n* 外连接\r\n\r\n  左外连接  有一主一次，当不满足条件时，左表完全显示，不满足内容为NULL\r\n\r\n  ```sql\r\n  SELECT e.ename , d.dname\r\n  FROM emp e LEFT OUT JOIN dept d\r\n  ON e.deptid=d.deptid\r\n  ```\r\n\r\n* 右外连接同理左外连接，右表为主\r\n\r\n* 全外为full outer join（mysql不支持）可以通过左外加右外加union关键字实现\r\n\r\n### 子查询\r\n\r\n子查询可以在 **from** 后，也可以在 **where** 后\r\n\r\n* 多行单列`sal > ALL(查找语句)` or`sal > ANY (查找语句)`  `IN`\r\n* 单行多列`select * from table_1 where (col_1,col_20) IN (select col_1,col_2 from table_2 where 条件)`\r\n* 多行多列`select * from table_1 别名1,(select ...)别名2 where 条件`\r\n\r\n\r\n\r\n\r\n\r\n关键字执行顺序（**select、from、where、group by、having、order by**）\r\n\r\n## 数据的备份与恢复\r\n\r\n* `mysqldump -p用户名 -u密码 数据库名>路径`\r\n* `mysqldump -p用户名 -u密码 数据库名<路径`\r\n* `在客户端里面 source 路径名`\r\n\r\n## 约束\r\n\r\n* PRIMARY KEY\r\n* NOT NULL\r\n* UNIQUE\r\n* AUTO_INCREMENT\r\n* `CONSTRAINT 约束名fk_从表_主表 FOREIGN KEY(本表被约束属性) CONFERENCES 主表(主表的主键)`\r\n\r\n#### 合并结果集\r\n\r\n```sql\r\nselect a , b from table_1\r\nunion all\r\nselect c , d from table_2\r\non 条件\r\n```\r\n\r\n加上`all`全部显示，不加的话完全相同的行会消除\r\n\r\n','2020-01-18 08:34:11','https://picsum.photos/id/1011/800/400','原创','','','mysql','2020-01-18 13:37:21',0,3,1,'测试markdown','','5'),(55,'\0','','## 堆树\r\n\r\n### 堆树的定义\r\n\r\n* 堆树是个完全二叉树\r\n* 堆树的某一个节点总是不大于或不小于其自己点\r\n* 堆树的子节点也是堆树\r\n\r\n## 堆树的操作\r\n\r\n以最大堆为例\r\n\r\n#### 存储方式\r\n\r\n堆的存储方式为数列`int [] heap = {0,1,2,3,4,5,6,7}; `\r\n\r\nheap[father]=heap[leftChild-1/2]\r\n\r\nheap[father]=heap[rightChild-1/2]\r\n\r\nheap[leftChild]=heap[father*2+1]\r\n\r\nheap[rightChild]=heap[father*2+2]\r\n\r\n#### 堆树的构建\r\n\r\n1. 找到最后一个节点的父节点，构建堆树\r\n2. 比较父节点与其较大的子节点交换、直到大于两子节点\r\n3. 往前循环直至head\r\n\r\n![1579876186330](https://img-blog.csdnimg.cn/20190109153038754.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01ha2VyQ2xvdWQ=,size_16,color_FFFFFF,t_70)\r\n\r\n#### 插入\r\n\r\n在PriorityQueue中由于实现了Queue接口有两种增加方法，add() 和 offer() 其区别在于add失败抛出异常，offer失败返回false\r\n\r\n**插入方式为增加在数组末尾，再向上进行判断修正堆树、调整方式为垂直上下（子节点中较大的）交换**\r\n\r\n#### 删除\r\n\r\n在PriorityQueue中由于实现了Queue接口有两种删除方法，remove() 和 poll() 其区别在于remove失败抛出异常，poll失败返回null\r\n\r\n删除时需要进行调整\r\n\r\n**先把数组末尾元素和删除元素交换，再进行堆树调整**\r\n\r\n#### 寻找最大元素\r\n\r\nQueue接口中为 element() 和 peek() 直接返回头部 ，element() 失败时抛出异常 ， peek() 返回null\r\n\r\n\r\n\r\n','2020-01-24 14:51:40','https://picsum.photos/id/1011/800/400','','','\0','最大堆树','2020-01-24 14:51:40',0,53,1,'最大(小)堆树的笔记','\0','54'),(56,'\0','\0','asdasd','2020-02-17 09:39:24','https://picsum.photos/id/1011/800/400','','','\0','This is blog1','2020-02-17 09:39:24',0,1,1,'1','','5'),(57,'\0','','222','2020-02-17 09:39:37','https://picsum.photos/id/1011/800/400','','','\0','This is blog2','2020-02-17 09:39:37',0,3,1,'2222','\0','54,7'),(58,'\0','','333','2020-02-17 09:59:10','https://picsum.photos/id/1011/800/400','','','\0','This is blog3','2020-02-17 09:59:10',0,1,1,'333','\0','7,54'),(59,'\0','','444','2020-02-17 09:59:43','https://picsum.photos/id/1011/800/400','','','\0','This is blog','2020-02-17 09:59:43',0,2,1,'444','\0','6,7'),(60,'','','555','2020-02-17 10:14:06','https://picsum.photos/id/1011/800/400','','','','This is blog555','2020-02-17 10:14:06',0,3,1,'555','','5'),(61,'','','6666','2020-02-17 13:42:02','https://picsum.photos/id/1011/800/400','','','\0','This is blog666','2020-02-17 13:42:02',0,1,1,'666','\0','5'),(62,'\0','\0','7777','2020-02-17 13:44:15','https://picsum.photos/id/1011/800/400','','','\0','This is blog7777','2020-02-17 13:44:15',0,2,1,'7777','\0','5'),(63,'\0','','8888','2020-02-17 13:47:11','https://picsum.photos/id/1011/800/400','','','','This is blog8888','2020-02-17 13:47:11',0,1,1,'8888','\0','5'),(64,'','','9999','2020-02-17 13:50:45','https://picsum.photos/id/1011/800/400','','','\0','This is blog999','2020-02-17 13:50:45',0,3,1,'9999','\0','5');
/*!40000 ALTER TABLE `t_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_blog_tags`
--

DROP TABLE IF EXISTS `t_blog_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_blog_tags` (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  KEY `FK5feau0gb4lq47fdb03uboswm8` (`tags_id`),
  KEY `FKh4pacwjwofrugxa9hpwaxg6mr` (`blogs_id`),
  CONSTRAINT `FK5feau0gb4lq47fdb03uboswm8` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`),
  CONSTRAINT `FKh4pacwjwofrugxa9hpwaxg6mr` FOREIGN KEY (`blogs_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_blog_tags`
--

LOCK TABLES `t_blog_tags` WRITE;
/*!40000 ALTER TABLE `t_blog_tags` DISABLE KEYS */;
INSERT INTO `t_blog_tags` VALUES (26,5),(26,6),(27,5),(41,5),(40,5),(29,5),(29,6),(29,7),(28,6),(28,7),(55,54),(56,5),(57,7),(57,54),(58,7),(58,54),(59,6),(59,7),(60,5),(61,5),(62,5),(63,5),(64,5);
/*!40000 ALTER TABLE `t_blog_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `admin_comment` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKke3uogd04j4jx316m1p51e05u` (`blog_id`),
  KEY `FK4jj284r3pb7japogvo6h72q95` (`parent_comment_id`),
  CONSTRAINT `FK4jj284r3pb7japogvo6h72q95` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`),
  CONSTRAINT `FKke3uogd04j4jx316m1p51e05u` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` VALUES (31,NULL,'asddddddddddddddddd',NULL,'asdasdbbb','asdsadaaa',28,NULL,'\0'),(32,NULL,'aaaaa',NULL,'asdasdbbb','asdsadaaa',28,NULL,'\0'),(33,NULL,'asdasd',NULL,'aaaa','asdasd',28,NULL,'\0'),(34,NULL,'asdasd',NULL,'dddd','aaa',28,NULL,'\0'),(35,NULL,'aa',NULL,'aa','aa',28,NULL,'\0'),(36,NULL,'asdsasd',NULL,'aa','aaaaa',28,31,'\0'),(37,NULL,'Comment test',NULL,'614989768@qq.com','yangshen',29,NULL,'\0'),(42,NULL,'留言测试',NULL,'614989768@qq.com','yangshen',41,NULL,'\0'),(43,NULL,'嵌套留言测试',NULL,'614989768@qq.com','yangshen',41,42,'\0'),(44,'/images/avatar.png','第一级','2020-01-18 16:08:24','bbb','aaa',41,NULL,'\0'),(45,'/images/avatar.png','一级','2020-01-18 16:12:14','bbb','aaa',41,NULL,'\0'),(46,'/images/avatar.png','123','2020-01-18 16:25:03','132','123',41,NULL,'\0'),(47,'/images/avatar.png','aaaaaa','2020-01-18 16:26:53','132','123',41,NULL,'\0'),(48,'/images/avatar.png','@aaa 二级','2020-01-18 16:27:04','132','123',41,45,'\0'),(49,'/images/avatar.png','@321321 三级','2020-01-18 16:27:14','132','123',41,48,'\0'),(50,'/images/avatar.png','测试一级','2020-01-18 16:51:32','aa','a',41,NULL,'\0'),(51,'/images/avatar.png','ceshi 2ji ','2020-01-18 16:51:41','aa','a',41,50,'\0'),(52,'/images/avatar.png','3','2020-01-18 16:51:46','aa','a',41,51,'\0');
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tag`
--

DROP TABLE IF EXISTS `t_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tag`
--

LOCK TABLES `t_tag` WRITE;
/*!40000 ALTER TABLE `t_tag` DISABLE KEYS */;
INSERT INTO `t_tag` VALUES (5,'SQL'),(6,'SpringbootS'),(7,'thymeleaf'),(54,'树');
/*!40000 ALTER TABLE `t_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_type`
--

DROP TABLE IF EXISTS `t_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_type`
--

LOCK TABLES `t_type` WRITE;
/*!40000 ALTER TABLE `t_type` DISABLE KEYS */;
INSERT INTO `t_type` VALUES (1,'异常日志'),(2,'Springboot'),(3,'MySQL'),(4,'blog'),(53,'数据结构');
/*!40000 ALTER TABLE `t_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'https://picsum.photos/id/1011/800/400',NULL,'614989768@qq.com','yangshen','81dc9bdb52d04dc20036dbd8313ed055',1,NULL,'yangshen');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-08 17:56:37
