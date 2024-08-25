# MoviesApp 🎬 (Jetpack Compose + Navigation + MVVM + Clean Architecture + Glide)
MoviesApp is an android app developed in Kotlin. It follows the principles of Clean Architecture, utilizes the MVVM (Model-View-ViewModel) architectural pattern, and is built using Jetpack Compose, Compose Navigation and third-party libraries.
This app enables users to see a list of most popular movies, search for their favourite movie and see detailed information. 

## App Features
- 📜 List: show a list of movies on a screen
- 🔍 Search: search functionality support in the list screen
- ↖️ Click: when a user clicks on a movie, we see detailed view of that movie 
- 🚫 Error handling for different states - loading, error, empty/placeholder

## Technologies
	- MVVM Architecture 
	- Kotlin
	- ViewModel
	- GSON - JSON Parser
	- Jetpack Compose for UI
	- Compose Navigation for the navigation framework
	- Coroutines
	- Third party libraries - Glide for images loading

## Architecture
The app follows the principles of Clean Architecture, which promotes separation of concerns and modular development. The architecture consists of the following layers:    
1. <b>data</b>   
     - Movie   
     - MoviesRepository   
     - movies.json -> mock data   
2. <b>UI</b>    
   - MovieApp   
   - MovieApp   
   - MoviesListScreen   
   - MovieDetailScreen     
3. <b>ViewModel</b>    
   - MoviesViewModelFactory   
   - MoviesViewModel   

## Development Workflow
The development workflow for the Movies App follows the standard MVVM pattern and Clean Architecture principles. 
	a. UI components are implemented using Jetpack Compose.
	b. ViewModel is created to hold and manage UI-related data and business logic.
	c. Data is stored locally in a json file and is fetched using Repository class

## Preview
#### Recording
#### Screenshots
| Feature | Screenshot |
| --- | --- |
| List | list img |
| Search | img |
| Detail | img |








