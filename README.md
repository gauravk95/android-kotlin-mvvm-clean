# Android Kotlin MVVM Multi Modules Clean app

## What is used?
- Kotlin
- Coroutines/Flow
- MVVM - Feature Based Multi-Module Clean Arch
- Apollo client for server communication
- Room for local db
- AAC - LiveData/Paging/Navigation etc.
- Glide for image loading
- Koin for Dependency Injection 
- Klint
- Others

## Clean Architecture
The architecture is based on the principles of Clean Architecture and is packaged by feature.

## Multi modules
The app follows the Clean Arch and uses modules for the different layers and features of your app.

Main modules:
- **app**: this is the "integration" module and the one that brings all the others together.
- **domain**: this contains the business logic, the interactors and the business models.
- **features**: one module per feature, where each feature has its own views, activity/fragments, viewmodels and so on. One feature does not depend on another.
- **data**: this contains everything related to data, local and remote. Data module is responsible for calling remote APIs, transforming response entities into domain objects and returning them to the interactors. Repositories are the main point of contact for interactors.
- **navigation**: this contains the actions to go from one feature to another. Intra feature navigation is done using Jetpack Navigation.
- **ui_components**: this is where project specific views, strings, colors, themes and so one, are put. This can be used by any feature module and is our "project UIKit".
- **common**: this contains code that all modules can use, possibly other projects as well. This one need to be as generic as possible because of that.
- **common_test**: this contains code that is used in all tests. Because of the way Gradle works, this had to be created as a separate module and should **only** be added as a dependency for testImplementation or androidTestImplementation.

## Notes
This project is work in progress

Improvements required: 

- [ ] Change the Single Source of Truth to Local Database(Room DB) 
- [ ] Better state handling & error handling
- [ ] Write tests - unit & ui

## License:
```
    Copyright 2021 Gaurav Kumar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```