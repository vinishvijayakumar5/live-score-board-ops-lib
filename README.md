#  Live Score Board operations library


## How to set up locally?

1. Install `Java 17`
2. Use any IDE, recommendations are IntelliJ or STS
3. Configure Maven in IDE
4. Run the root project pom xml `live-score-board-ops-lib/pom.xml`
5. Tada... if no errors observed, then the module has been installed in your local maven repository and ready to use.

## How to use the library in your project

1. Import the library as the dependency in your project with correct version  `<groupId>com.zyxcorp.libs.scoreboard</groupId>
   <artifactId>libs-scoreboard</artifactId>`
2. Inject `com.zyxcorp.libs.scoreboard.MatchScoreboard` interface which has usable methods to create, update, delete and get match
