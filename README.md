# Pre-work - Pomo List
![alt tag](http://i.imgur.com/cpZ51UJ.png)

This is a todo app with a ketch(up). PomoList is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item. You can add your todo items to a pomodoro list and start a timer. This way you can time your todos and keep yourself on schedule.

Submitted by: Victor Hom

Time spent: 10 hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **successfully add and remove items** from the todo list
* [X] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [X] User can **persist todo items** and retrieve them properly on app restart

Information:
You can add your high level one liner todo item on the main screen. If you tap on the item, you can get into a more detailed view where you can update information about your todo. Information that can be updated include the high level one liner, the date that your todo is due, whether to add it to your pomodoro timer, and a note area to go into more detail.

The following **optional** features are implemented:

* [X] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [X] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [X] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [X] Add support for selecting the priority of each todo item (and display in listview item)
* [X] Tweak the style improving the UI / UX, play with colors, images or backgrounds

Information:
* With the help of Sugar ORM to help with the abstraction, it was easy to work with a backend without thinking too much about my queries.
* I created a custom adapter to handle a list of Todo items. The custom adapter also made it possible for me to customize each todo task view, such as turning the line item to be red when the priority level is 1 and to have an icon if the item is added to the pomodoro list.
* A DialogFragment was used for the todo item edit feature.
* I created the app icon with Sketch and the setting, exit, and save icon were taken from http://www.flaticon.com/packs/android-app.

The following **additional** features are implemented:

* [X] List anything else that you can get done to improve the app functionality!

Adding todo items to a timer. You can also set the timers (1 - 25 minutes) for the task time and the rest time (1-10 minutes) in the settings.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/5PfHiRE.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

* Not knowing the ins/outs of life cycle methods
* Interactions between activities and fragments
* Handling state on going back and forth between activities and fragments
* Layout issues

## License

    Copyright 2016 Victor Hom

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
