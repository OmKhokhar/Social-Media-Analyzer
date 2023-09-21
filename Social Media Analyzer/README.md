Social Media Analyzer
---------------------
Welcome to the Assignment 1 of Advanced Programming. This project has been done by Om Khokhar(s3886577). This project contains tools and algorithms to analyze data related to social media posts.

File Structure

Below is a brief overview of the main directories and files in this project:

- src: This directory contains the main source code of the project.

    - exceptions:
        - InvalidMenuOption.java: Represents an exception thrown when an invalid menu option is selected.
        - InvalidPostData.java: Represents an exception thrown when the data related to a post is invalid or does not meet certain criteria.

    - main:
        - SocialMediaAnalyzer.java: This is the main entry point of the application. It provides a menu-driven interface to interact with the functionalities offered by the application.

    - manager:
        - PostManager.java: Manages operations related to social media posts, such as loading posts from a CSV file, saving them, adding new posts, and retrieving top posts based on likes and shares.
    
    - model:
        - Post.java: Represents the data model for a single social media post. It contains fields like ID, content, author, likes, shares, and date-time.

- test: This directory contains the unit tests for the project.

    - PostManagerTest.java: Contains unit tests for the PostManager class, ensuring functionalities like adding, deleting, and retrieving posts work as expected.

    - PostTest.java: Contains unit tests for the Post data model, ensuring the integrity and correctness of the data representation.

- pom.xml: This is the Maven Project Object Model file. It contains configurations, plugins, dependencies (like JUnit for testing), and other settings needed for building and testing the project.

- post.csv: The csv file provided which contains the info about the posts

- README.md: This file which you are currently in. This file provides you info about how the project has been set up and how you can run and test the code.

Note on JUnit Setup
-------------------
This project does not use  JAR files for JUnit. Instead, I have used Maven for dependency management. The pom.xml file in the root directory specifies a dependency for JUnit 5. When you build or test the project using Maven commands, it automatically fetches the required JUnit libraries, ensuring a clean and streamlined setup without manual downloads or configurations.

If you wish to explore or adjust the JUnit version, or other related settings, please refer to the <dependencies> section in the pom.xml file.



Running the Social Media Analyzer from Command Line
---------------------------------------------------
To run the Social Media Analyzer from the command line, follow the steps below:

- Navigate to the Project Directory:
    Open your command prompt or terminal and navigate to the root directory of the project, i.e., where the pom.xml file is located.

- Compile the Project:
    mvn clean compile

- Run the Application:
    mvn exec:java -Dexec.mainClass="main.SocialMediaAnalyzer"

- Running Tests:
    mvn test