## 本项目实现的最终作用是基于JSP在线网络文件管理分享网站平台
## 分为3个角色
### 第1个角色为管理员角色，实现了如下功能：
 - 添加学生
 - 添加文件
 - 添加管理员
 - 添加老师
 - 管理员登录
### 第2个角色为教师角色，实现了如下功能：
 - 上传文件
 - 修改个人信息
 - 按分类查看
 - 教师登录
### 第3个角色为学生角色，实现了如下功能：
 - 上传文件
 - 修改个人信息
 - 学生登录
 - 按分类查看
## 数据库设计如下：
# 数据库设计文档

**数据库名：** filemanage_site

**文档版本：** 


| 表名                  | 说明       |
| :---: | :---: |
| [klfile](#klfile) |  |
| [manager](#manager) |  |
| [student](#student) | 学生信息表 |
| [studentfile](#studentfile) |  |
| [teacher](#teacher) | 教师信息表 |
| [teacherfile](#teacherfile) |  |

**表名：** <a id="klfile">klfile</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | klfile_ID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | klfile_fileName |   varchar   | 255 |   0    |    N     |  N   |       | 文件名  |
|  3   | klfile_author |   varchar   | 255 |   0    |    N     |  N   |       | 上传者  |
|  4   | klfile_uploadingTime |   varchar   | 255 |   0    |    N     |  N   |   '2023-01-01'    | 上传时间  |
|  5   | klfile_visitTime |   int   | 10 |   0    |    N     |  N   |       | 浏览次数  |
|  6   | klfile_downloads |   int   | 10 |   0    |    N     |  N   |       | 下载次数  |
|  7   | klfile_fileType |   varchar   | 255 |   0    |    N     |  N   |       | 文件类型  |
|  8   | klfile_fileSize |   double   | 23 |   0    |    Y     |  N   |   NULL    | 文件大小  |
|  9   | klfile_documentClassification |   varchar   | 255 |   0    |    N     |  N   |       | 所在类型  |
|  10   | klfile_savepath |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  11   | klfile_describtion |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |
|  12   | klfile_uuid |   varchar   | 255 |   0    |    Y     |  N   |   NULL    |   |

**表名：** <a id="manager">manager</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | manager_ID |   int   | 10 |   0    |    N     |  Y   |       | 管理员ID  |
|  2   | manager_number |   varchar   | 255 |   0    |    N     |  N   |       | 账号  |
|  3   | manager_password |   varchar   | 255 |   0    |    N     |  N   |       | 登陆密码  |
|  4   | manager_lastTime |   datetime   | 19 |   0    |    Y     |  N   |   NULL    | 最后登录时间  |
|  5   | manager_loginTime |   int   | 10 |   0    |    Y     |  N   |   0    | 登陆次数  |

**表名：** <a id="student">student</a>

**说明：** 学生信息表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | student_ID |   int   | 10 |   0    |    N     |  Y   |       | 学生ID  |
|  2   | student_number |   varchar   | 255 |   0    |    N     |  N   |       | 学号  |
|  3   | student_password |   varchar   | 255 |   0    |    N     |  N   |       | 登陆密码  |
|  4   | student_name |   varchar   | 255 |   0    |    N     |  N   |       | 姓名  |
|  5   | student_phone |   varchar   | 11 |   0    |    N     |  N   |       | 电话  |
|  6   | student_email |   varchar   | 255 |   0    |    N     |  N   |       | 邮箱  |
|  7   | student_QQ |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | qq  |
|  8   | student_weChat |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 微信  |
|  9   | student_brithday |   varchar   | 255 |   0    |    Y     |  N   |   '1990-01-01'    | 生日  |
|  10   | student_class |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 班级  |
|  11   | student_academy |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 学院  |
|  12   | student_strongPoint |   varchar   | 255 |   0    |    N     |  N   |       | 知识特长  |
|  13   | student_loginTime |   int   | 10 |   0    |    Y     |  N   |   NULL    | 登陆次数  |
|  14   | student_lastTime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 最后登录时间  |

**表名：** <a id="studentfile">studentfile</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | studentFile_ID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | studentFile_fileName |   varchar   | 255 |   0    |    N     |  N   |       | 浏览过的文件名  |
|  3   | studentFile_browseTime |   varchar   | 255 |   0    |    N     |  N   |   '2023-01-01'    | 浏览时间  |
|  4   | studentFile_download |   int   | 10 |   0    |    N     |  N   |   0    | 是否下载过(0没下载过)  |
|  5   | studentFile_studentName |   varchar   | 255 |   0    |    N     |  N   |       | 学生姓名  |
|  6   | studentFile_uploading |   int   | 10 |   0    |    N     |  N   |   0    | 是否是本人上传(1为本人上传)  |
|  7   | studentFile_evaluate |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 评价  |

**表名：** <a id="teacher">teacher</a>

**说明：** 教师信息表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | teacher_ID |   int   | 10 |   0    |    N     |  Y   |       | 老师ID  |
|  2   | teacher_number |   varchar   | 255 |   0    |    N     |  N   |       | 考试登陆账号  |
|  3   | teacher_password |   varchar   | 255 |   0    |    N     |  N   |       | 登陆密码  |
|  4   | teacher_name |   varchar   | 255 |   0    |    N     |  N   |       | 老师密码  |
|  5   | teacher_title |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 老师职称  |
|  6   | teacher_duty |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 职务  |
|  7   | teacher_department |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 部门  |
|  8   | teacher_office |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 办公室  |
|  9   | teacher_phone |   varchar   | 11 |   0    |    N     |  N   |       | 电话  |
|  10   | teacher_telephone |   varchar   | 11 |   0    |    Y     |  N   |   NULL    | 固定电话  |
|  11   | teacher_email |   varchar   | 255 |   0    |    N     |  N   |       | 老师邮箱  |
|  12   | teacher_academy |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 老师院系  |
|  13   | teacher_honor |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 老师获奖情况  |
|  14   | teacher_loginTime |   int   | 10 |   0    |    Y     |  N   |   NULL    | 登陆次数  |
|  15   | teacher_lastTime |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 最后登录时间  |

**表名：** <a id="teacherfile">teacherfile</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|  1   | teacherFile_ID |   int   | 10 |   0    |    N     |  Y   |       |   |
|  2   | teacherFile_fileName |   varchar   | 255 |   0    |    N     |  N   |       | 老师浏览文件名  |
|  3   | teacherFile_browseTime |   varchar   | 255 |   0    |    Y     |  N   |   '2023-01-01'    | 浏览时间  |
|  4   | teacherFile_download |   int   | 10 |   0    |    N     |  N   |   0    | 是否被下载过(0为没被下载过)  |
|  5   | teacherFile_teacherName |   varchar   | 255 |   0    |    N     |  N   |       | 教师姓名  |
|  6   | teacherFile_uploading |   int   | 10 |   0    |    Y     |  N   |   0    | 是否为本人上传(1为本人上传)  |
|  7   | teacherFile_evaluate |   varchar   | 255 |   0    |    Y     |  N   |   NULL    | 对文件的评价  |

**运行不出来可以微信 javape 我的公众号：源码码头**
