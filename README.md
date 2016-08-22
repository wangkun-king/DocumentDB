# DocumentDB
Azure DocumentDB Test
如果报SSL和认证错误，原因是JDK没有安装证书

安装过程如下：

JDK WoSign 证书安装：
A. 从http://www.wosign.com/Root/index.htm# 站点 下载WoSign 根证书（Certification Authority of WoSign），将.crt 文件后缀改为 .cer
B. 执行以下命令导入
      a. keytool -keystore "C:\Program Files\Java\jdk1.8.0_71\jre\lib\security\cacerts" -importcert -alias WoSign -file WS_CA1_NEW.cer
      b. 接下来 会提示输入密码，默认密码为 changeit，输入之后，选择‘是’将其安装到JDK 可信证书库中。
C. 如果看到以下结果，则导入成功。
