# MovieZone
Senior Design Project - Nihad Šuvalija

Architecture used for this project is MVVM.

Design of the application has been found on Figma (www.figma.com).
https://www.figma.com/community/file/1088719884686291024

Implemented so far (20.4.2022 update):
    Splash screen:
        - Logo, timer and navigation.
    Sign in screen:
        - Sign in button functionality.
        - Authorization over Firebase.
    Sign up screen:
        - Design.
        - Email sign up functionality.
        - Google sign up functionality.
        - Navigation.
    Movie details screen:
        - Movie poster display.
        - Movie details display.
        - Movie rating display.
        - Movie release year, runtime and primary genre display.
        - Watch trailer button functionality.
        - Movie storyline display.
        - Movie cast display.
        - Movie reviews display.
    Home screen:
        - Discover section.
        - Now playing category.
        - Upcoming category.
        - Top rated category.
        - Clickable movies.
        - No internet connection issue of loading the movies.
    Search screen:
        - Search implementation.
        - Search string persistency after leaving the screen.
        - No results display.
        - Search items design.
        - Clickable items.
    Profile screen:
        - Sign out button functionality.
        - Legal and privacy functionality.
    

Things used for the project:
    - Data Binding
    - Glide (plugin for loading pictures into ImageView)
    - Firebase (Realtime Database, Firestore) - POTENTIAL UPDATE IN THE FUTURE TO PERSONALLY HOSTED DATABASE
    - TMDB API
    - YouTube API
    - Retrofit (for API calls)
    - Coroutines and flows
