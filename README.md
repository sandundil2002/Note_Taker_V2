<a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&weight=600&size=50&pause=1000&center=true&vCenter=true&width=835&height=70&lines=Note+Taker+V2" alt="Typing SVG" /></a>

<p id="description">Welcome to the Note Taker V2 project repository! Here you'll find a Spring Boot application designed for note management boasting three distinct branches that implement varying levels of security. Dive into the repository to explore the different security features from no security to basic authentication to advanced JWT-based authentication.</p>

<h2>Branches</h2>

  1) master: This branch contains the basic version of the project with no security features implemented.
  2) secure-basic: This branch adds basic authentication and authorization features using Spring Security.
  3) secure-jwt: This branch enhances security by implementing JWT (JSON Web Token) authentication.

<h2>API Endpoints</h2> 

  Authentication
  
  * POST /api/v1/auth/signup: Registers a new user.
  * POST /api/v1/auth/signin: Authenticates an existing user and returns a JWT.
  * POST /api/v1/auth/refresh: Refreshes the JWT.

  Notes

  * POST /api/v1/notes: Creates a new note.
  * GET /api/v1/notes/allnotes: Retrieves all notes (Admin only).
  * GET /api/v1/notes/{noteId}: Retrieves a note by ID.
  * PATCH /api/v1/notes/{id}: Updates a note.
  * DELETE /api/v1/notes/{id}: Deletes a note.

  Users

  * DELETE /api/v1/users/{id}: Deletes a user.
  * GET /api/v1/users/{id}: Retrieves user details by ID.
  * GET /api/v1/users: Retrieves all users (Admin only).
  * PATCH /api/v1/users/{id}: Updates user details.
    
  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   Create new notes
*   View all notes
*   View a specific note by ID
*   Update existing notes
*   Delete notes
*   User management (create update delete users)
*   File upload for user profile pictures

<h2>🛠️ Installation Steps:</h2>

<p>1. Clone the repository:</p>

```
https://github.com/sandundil2002/Note_Taker_V2.git
```

<p>2. Ensure your application.properties file has the correct database configuration:</p>

```
spring.datasource.url=jdbc:mysql://localhost:3306/note_taker?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false spring.datasource.username=root spring.datasource.password=your_password spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

<p>3. Checkout the branch you want to explore:</p>

```
git checkout 
```

<p>4. Build and run the application:</p>

```
./gradlew bootRun
```

<h2>API Documentation</h2>

<a href="https://documenter.getpostman.com/view/35384990/2sAXxV4p5W" >View API Documentation</a>
  
  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Spring Boot: Framework to simplify the development of Java-based enterprise applications.
*   Spring Security: Security framework for authentication and authorization.
*   JWT (JSON Web Token): For secure communication between client and server.
*   Hibernate: ORM framework for database interaction.
*   MySQL: Relational database management system.
*   Gradle: Build automation tool.
*   ModelMapper: Object mapping library to simplify code.
*   Lombok: Library to reduce boilerplate code.
