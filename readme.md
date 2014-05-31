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
Using eclemma (code coverage) and the constants used in the updateQuality, I came up with 4 items x 4 sellIns x 4 quality values number of executions.
First modification to the updateQuality method was to extract constants for items names and for quality values. Green. 
Commit. Tag v0.2. Push to github (* git push origin master)
Gone to bed

3. Clean up
Next day in the morning. Clean up this file and added the Goals section
Commit. Tag v0.3. Push to github.

4. Extract logic in enum ItemType
TDD ItemTypes for Normal items, AgedBrie, Legendary items and Backstage passes.
Create a getType methode in GildedRose to map the name to a type
Execute lockdown test.
I got this kind of differences as the maximum quality is now capped for aged brie and backstage passes. Ok
before: [Aged Brie, 5, 80] = 1 items : Item [name=Aged Brie, sellIn=4, quality=80]
after:  [Aged Brie, 5, 80] = 1 items : Item [name=Aged Brie, sellIn=4, quality=50]    

I got also this one, which means that I have a regression around thresholds for backstage passes. I corrected the tests and the code in ItemType.
before: [Backstage passes to a TAFKAL80ETC concert, 11, 0] = 1 items : Item [name=Backstage passes to a TAFKAL80ETC concert, sellIn=10, quality=1] 
after:  [Backstage passes to a TAFKAL80ETC concert, 11, 0] = 1 items : Item [name=Backstage passes to a TAFKAL80ETC concert, sellIn=10, quality=2] 

So back to step 1. I rewrote the lockdown test to cover all sell in dates between 13 and -1 and to 'ignore' incoherent combinations. 
Run test with original updateQuality, Approve.
Rerun test with my version. Green.
Commit. Tag v0.4. Push to github. 

5. Implement Conjured Item type
Separated quality logic from sellIn logic in ItemType
TDD Conjured ItemType.
Created enum Quality and class ItemNames (single responsability)
Last, I should have deleted GildedRose.toString and GildedRoseTest lockdown test, but they are still useful as there aren't other tests for GildedRose class. So, I added a conjured item to the list, rerun the test ad approved the new 'true' result.
Commit. Tag v1. Push to github.

That's all

What have I learned
- some basic git commands
- 100% code coverage hides some possibles pitfalls. ApprovalTests should be executed with more values than those necessary for 100% coverage. One should look at the code and decide if there are enough combinations.
- shortcuts Eclipse : CTRL+F6, Alt+Shift+X+T, Alt+Shift+E+T / Chrome : Ctrl+L. I need a lot more practice without mouse.
- Some maven 'magic' to create project local repositories for libs that aren't present in any public repository

    