# Articles

A simple App to	hit	the	NY Times	Most	Popular	Articles	API	and	show	a	list	of	articles,	
and	shows article	details	when	items	on the	list	are	tapped.


## App Features
1. User can list most popular articles in last 7 days.
2. User can tap on article and view article details.

## App architecture
Based on mvvm architecture and repository pattern.

### The app includes the following main components:
 
* A web API service.
* A repository that works with the API service to provide a unified data interface.
* A dependacy injection framework to handly the services and ViewModels creation and injection.
* ViewModels that provide data specific to the UI using RxJava and LiveData.
* The UI, which shows a visual representation of the data in the ViewModel.

### App Packages:
* **data** - contains:
  * **repo** -  repository classes for handling app data.
  * **remote** - contains classes needed for making API calls to NyTimes server using Retrofit.
* **di** - contains dependency injection classes, using Dagger2.
* **ui** - contains classes needed to display Activities and Fragments.
* **utils** - contains app constants and Utils classes.


### App Specs
* Minimum SDK 16.
* Kotlin.
* MVVM Architecture.
* JetPack.
* Android Architecture Components (LiveData, Lifecycle, ViewModel, ConstraintLayout, Navigation component)
* Dagger 2 for dependency injection.
* Retrofit 2 for API integration.
* RxJava for making API calls.
* Gson for serialisation.
* Junit 4 for testing.
