# mersys.io API Testing using REST Assured

![Static Badge](https://img.shields.io/badge/test-%20pass%20-s?style=for-the-badge&labelColor=darksilver&color=darkgreen)
[![MIT License](https://img.shields.io/badge/licence-%20MIT%20-s?style=for-the-badge&labelColor=darksilver&color=darkgreen)](https://choosealicense.com/licenses/mit/)

This project is created to perform API tests for mersys.io, an educational application, with the aim of enhancing user experience.

### Mersys Campus
- **Frontend Test Project :** [mersys.io_Testing](https://github.com/Pazzoide/mersys.io_Testing.git)<br>
- **API Test with POSTMAN :** [mersys.io_API_Postman](https://github.com/Pazzoide/mersys.io_API_Testing.git)

While Starting
---------------
### The simplest method :
-  ***Follow the path***
      * File > New > Project From Version Control

  -  ***Copy URL and paste***
     -     https://github.com/Pazzoide/mersysio_restassured.git 

-  ***Open this project at your IDE***
   * ***Select JDK version for project***
       * jdk1.8 
   *  ***To load the POM with Maven,click on***
       * pom.xml > Maven > Reload Project 

<img src="https://www.animatedimages.org/data/media/562/animated-line-image-0184.gif" width="1920" />

![Jenkins](JenkinsAPI.gif)

<img src="https://www.animatedimages.org/data/media/562/animated-line-image-0184.gif" width="1920" />


### Dependencies

<img src="https://www.animatedimages.org/data/media/562/animated-line-image-0426.gif" width="1920" />

<div align="right">
  <table style="width: 100%; table-layout: fixed; border-collapse: collapse; height: 100%;">
    <thead>
      <tr>
        <th style="width: 33.33%;"><img src="https://img.shields.io/badge/RestAssured-v5.2.0-darkgreen?style=for-the-badge" alt="RestAssured" /></th>
        <th style="width: 33.33%;"><img src="https://img.shields.io/badge/TestNG-v7.4.0-darkgreen?style=for-the-badge" alt="TestNG" /></th>
        <th style="width: 33.33%;"><img src="https://img.shields.io/badge/Hamcrest-v1.3-darkgreen?style=for-the-badge" alt="Hamcrest" /></th>
      </tr>
      <tr>
        <td style="width: 33.33%;">A Java library designed for comprehensive REST API testing, providing a robust open-source testing framework.</td>
        <td style="width: 33.33%;">TestNG is a powerful open-source testing framework for Java that can be used to efficiently automate a wide range of testing tasks and scenarios.</td>
        <td style="width: 33.33%;">A Java library dedicated to data matching and assertion, assisting in ensuring data integrity and consistency.</td>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th style="width: 33.33%;"><img src="https://img.shields.io/badge/Jackson-v2.14.1-darkgreen?style=for-the-badge" alt="Jackson" /></th>
        <th style="width: 33.33%;"><img src="https://img.shields.io/badge/Apache POI--OOXML-v4.1.0-darkgreen?style=for-the-badge" alt="ApachePOI-OOXML" /></th>
        <th style="width: 33.33%;"><img src="https://img.shields.io/badge/Apache POI-v4.1.0-darkgreen?style=for-the-badge" alt="ApachePOI" /></th>
      </tr>
      <tr>
        <td style="width: 33.33%;">A Java library specialized in the processing of JSON data, simplifying data manipulation and transformation tasks.</td>
        <td style="width: 33.33%;">An extension of Apache POI optimized for modern Office document formats, enhancing compatibility and performance in document operations.</td>
        <td style="width: 33.33%;">A Java library tailored for seamless handling of Microsoft Office documents, offering functionality for document management.</td>
      </tr>
      <tr>
	<th style="width: 33.33%;"><img src="https://img.shields.io/badge/JavaFaker-v1.0.2-darkgreen?style=for-the-badge" alt="JavaFaker" /></th>
        <td style="width: 66.67%;" colspan="2">JavaFaker is a versatile and user-friendly Java library that provides realistic fake data for use in project development, testing, and presentations.</td>
      </tr>
    </tbody>
  </table>
</div>




### File Structure

<img src="https://www.animatedimages.org/data/media/562/animated-line-image-0426.gif" width="1920" />

```text
├── src
│   └── test
│       └── java
│           ├── Campus_API_Testing
│           │   └── API Testing Classes files 
│           ├── ExcelFiles
│           │   └── LoginInfo.xlsx
│           ├── Utilities
│           │   └── ExcelManager
│       
├── Test Excel Reports
│   └── ExcelOutput.xlsx
├── .gitignore
└── pom.xml
```

### TEAM

<img src="https://www.animatedimages.org/data/media/562/animated-line-image-0426.gif" width="1920" />

- <h4>T.Lead <| Burhan SENGUL <br /><strong> <a href="mailto:burhseng@gmail.com"> <img src="https://www.freepnglogos.com/uploads/logo-gmail-png/logo-gmail-png-gmail-icon-download-png-and-vector-1.png" alt="E-Posta" width="31" height="25" /></a></strong></h4>
- <h4>Tester <| D. Fatihhan KARAHANLI <br /><strong> <a href="mailto:fatihhan.karahanli@gmail.com"> <img src="https://www.freepnglogos.com/uploads/logo-gmail-png/logo-gmail-png-gmail-icon-download-png-and-vector-1.png" alt="E-Posta" width="31" height="25" /></a></strong></h4>
- <h4>Tester <| Şeyma ASKERZADE <br /><strong> <a href="mailto:4seyma@gmail.com"> <img src="https://www.freepnglogos.com/uploads/logo-gmail-png/logo-gmail-png-gmail-icon-download-png-and-vector-1.png" alt="E-Posta" width="31" height="25" /></a>&nbsp;</strong></h4>
- <h4><strong>Tester <| Ramazan INCE </strong><br /><strong><a href="mailto:inceliramazan80@gmail.com"><img src="https://www.freepnglogos.com/uploads/logo-gmail-png/logo-gmail-png-gmail-icon-download-png-and-vector-1.png" alt="E-Posta" width="31" height="25" /></a>&nbsp;</strong></h4>
- <h4>Tester <| Zafer CANSIZ <br /><strong><a href="mailto:zfrcnsz1@gmail.com"><img src="https://www.freepnglogos.com/uploads/logo-gmail-png/logo-gmail-png-gmail-icon-download-png-and-vector-1.png" alt="E-Posta" width="31" height="25" /></a>&nbsp;</strong></h4>



### TODO
<img src="https://www.animatedimages.org/data/media/562/animated-line-image-0426.gif" width="1920" />

* It's crucial to stick to the specified dependencies versions to maintain compatibility and reliability.
  Using different versions can jeopardize project stability and compatibility.
  In case you encounter problems with alternative versions, please note that these
  issues cannot be attributed to the project.<br>
  <br>
* If you experience issues or compatibility problems with a specific version of our project or
  if you are tempted to use different versions, we encourage you to provide feedback. We will
  do our best to assist you.

### LICENSE
<img src="https://www.animatedimages.org/data/media/562/animated-line-image-0426.gif" width="1920" />

- This project is an open-source software licensed under the terms of the[ MIT license](https://opensource.org/license/mit/).

