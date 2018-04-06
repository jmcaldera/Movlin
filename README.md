# Movlin: Movies Application written in Kotlin using Clean Architecture
This is a work in progress.

The goal of this app is to apply new concepts and techniques into a Clean Architecture approach using Kotlin, and at the same, tackle some topics I'm less familiar with or are new to me. Some of this topics are:

- Kotlin Language
- Kotlin Coroutines
- Dagger 2
- Unit testing
- Functional Programming

## How to use this project
You will need an [API Key from The Movie DB](https://developers.themoviedb.org/3/getting-started/introduction) (it's free). Then create a resource file `app/src/main/res/values/api_key.xml` with the following content:
```
<string name="the_movie_db_api_key">YOUR_API_KEY</string>
```

## Libraries included
- Kotlin
- Kotlin Coroutines
- Picasso
- Dagger2
- Retrofit
- LeakCanary
- JUnit and Mockito (+ Mockito inline for mocking final classes)
- Android KTX
