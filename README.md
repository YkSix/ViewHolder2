# The last ViewHolder

[![API](https://img.shields.io/badge/API-7%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=7) <img src="https://img.shields.io/badge/method%20count-52-e91e63.svg"></img> <img src="https://img.shields.io/badge/size-7 KB-e91e63.svg"></img>

With this library, you no longer need to customize RecyclerView.ViewHolder classes or add one-time-use interfaces for click listeners anymore.

Customizing RecyclerView.ViewHolder classes are extremely common in Android projects. These classes are used for binding views with data. That's right: we're talking about those custom ViewHolder classes where you wind up adding lots of one-time-use variables in a bloated, repetitive and formulaic yet error-prone fashion.

Adding these member variables and binding them one time is not so bad. But once written, they continue to burden reviewers and future readers with extra code.

The ViewHolder class provides a more elegant way to bind data to RecyclerView, with a lot less code and less room for error.

Save your time. Save your code. Save your sanity.
