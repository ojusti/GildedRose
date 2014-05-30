Gilded Rose
===========
Java version copied from: https://github.com/wouterla/GildedRose

1. Lockdown actual behavior : 
We should have feedback / sense. Make List items non static (I hope it is permitted). Same thing for updateQuality method. Create a constructor that takes a List. Create a builder method makeDefault.
Add a toString method to GildedRose class (because we cannot modify Item class)
Add AprovalTest with meld as a local dependency (mvn)
Write the lockdown test. Red/Approve/Green. Commit. Tag v0.1

2. Refactoring updateQuality
I realized that my test was not so good. Rewrite and use LegacyApprovals.LockDown to tackle complexity. Downloaded eclemma and check coverage. 
Extract constants. Green. Commit. Tag v0.2. Go to bed