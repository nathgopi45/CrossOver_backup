Challenge Statement

Cross-blogs is a backend blogging application written by a startup company called WritingForAll. It allows users to create / update / delete their articles, accepting comments for each article.

Notes:
- Articles have a 120 character limit for their title, and a 32k limit for their body.
- The frontend application is excluded from the current scope. It is a separate, fully-functional application handled by another team, so we do not want to modify it.


Your tasks:
- Increase unit test coverage to reach 70%, achieving more than 70% will only consume your valuable time without extra score.
- Find bugs and fix them, hint: we provided Cross-Blogs application in a good structure, so no need to spend your valuable time on structure modifications,  just focus on fixing bugs.
- Articles search endpoint is very slow, please optimize it.
- Recently Crossover acquired Cross-Blogs and found that Cross-Socials -it is another project for social networking owned by crossover - is very eligible to promote Cross-Blogs,  they want to display articles only in Cross-Socials news feed without comments using get article endpoint, the problem is Cross-Blogs traffic was 10M page views per day while Cross-Socials page views are 900M per day, Your goal is to scale Cross-Blogs app to serve expected traffic, Cross-Blogs web application was hosted in a single huge AWS node and the node was loaded for 10M per day’s traffic which makes increasing node size not a valid option, the conclusion is we need to have multi nodes of Cross-Blogs, and offload database.
    Notes about last task:
    - Perform required modifications in application level, and in case application modifications requires another 3rd party please add it to docker-compose.yml file.
    - Database replication is not required in this task.
    - Pay attention to articles only, and feel free to exclude comments from current scope of scaling as comments will not appear in Cross-Social at all.

Prerequisites:
	Any IDE
	Java 8
  Docker (follow this link to install https://store.docker.com/search?type=edition&offering=community)


Development Environment:
  Mysql:
    we assume that no mysql available in your machine, if it is running please stop it to avoid port conflicts, and use following command to run pre-configured mysql
      docker-compose -f mysql.yml up
  Crossblogs Application:
    To start the application run CrossBlogsApplication.java main method from your IDE
    and to check that it is running open http://localhost:8080/articles/1 from your browser.


Production Environment:
  This is how we are going to run and evaluate your submission, so please make sure to run below steps before submit your answer.

  1) Dockerize CrossBlogs:
    - in application.properties set database host to be "crossblogs-mysql" as below
      spring.datasource.url: jdbc:mysql://crossblogs-mysql:3306/crossblogs?useSSL=false
    - create app image using below command
      ./gradlew clean bootJar buildDocker
    - If you add any new 3rd party application, please add it to docker-compse.yml file exactly like mysql


  2) Start whole environemnt using below command and make sure that everything is healty
    docker-compose up    // To terminate everything you can use ctrl+c

  3) Remove any build directories, compress whole project, and rename zipfile to be crossblogs-java-<YOUR-FULL-NAME>.zip. Please use dash "-" instead of any space



