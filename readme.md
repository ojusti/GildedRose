Gilded Rose
===========
Java version copied from: https://github.com/wouterla/GildedRose
0. Goals
Use git from the command line. Push to Github
Use ApprovalTests tool
Learn Eclipse shortcuts for navigating between editors
* marks the things I did for the first time

1. Lockdown actual behavior
The GildedRose class doesn't provide any feedback. Make List items non static (is it permitted?). Make updateQuality method non static. Create a constructor that takes a List. Create a builder method makeDefault.
Add a toString method to GildedRose class that will allow us to get feedback.
Add ApprovalTests lib (integration with diff tool) as a local dependency (maven) (* https://devcenter.heroku.com/articles/local-maven-dependencies)
Write the lockdown test using ApprovalTests. Red/Approve/Green. (* technically not a first, as I already did this today at http://2014.itakeunconf.com/johan-martinsson-remy-sanlaville-100-confident-with-legacy-code/)
Commit. (* from command line : git add .; git commit -am '..') 
Tag v0.1 (* git tag -a v0.1 -m '...')

2. Refactoring updateQuality
Refactoring updateQuality was the initial plan. I realized that my test was not very good. I rewrote it and used LegacyApprovals.LockDown to tackle combinatorial complexity (*). 
Using eclemma (code coverage) and the constants used in the updateQuality,  method  and check coverage. 
Extract constants. Green. Commit. Tag v0.2. Go to bed