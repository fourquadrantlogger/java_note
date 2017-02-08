## 一 配置settings.xml
 
因为nexus是需要登陆操作，当然可以通过配置免登陆，这是后话。
在settings.xml的<servers></servers>
<server>   
<id>thirdparty</id>   
<username>admin</username>
<password>admin123</password>   
</server>
 
当然如果你要上传包去其他仓库，可依照此例，如
 
<server>   
<id>central</id>   
<username>admin</username>   
<password>admin123</password>   
</server>
 
如果进行deploy时返回Return code is: 401错误，则需要进行用户验证或者你已经验证的信息有误。

## gpg 钥匙

## deploy
sudo apt install maven
mvn clean deploy -P release
