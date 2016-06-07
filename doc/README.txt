cd /workspace/luna/spring_boot_tomcat
git diff
git add -A
git commit -a -m "ok"
git push

curl -v -H "Accept: application/json" "http://localhost:8080/mvc/account/member/11.json" | python -m json.tool
curl -v -H "Content-type: application/json" -H "Accept: application/json" -H "Accept-Encoding: gzip" -H "Accept-Encoding: gzip, deflate" "http://localhost:8080/mvc/account/member/11.json"




https://github.com/alx3apps/spring-embedded-tomcat
https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat
http://www.robinhowlett.com/blog/2013/02/13/spring-app-migration-from-xml-to-java-based-config/
