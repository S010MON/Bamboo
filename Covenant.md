
# Group 16 Covenant 

This covenant dictates how, where, and what we work on for Project 2.1.  It is agreed to by the team with a majority vote and any changes can be made with a team majority in favour of proposals.  In the case of less than full attendance, a quorum of 5 people should be present to make changes.

### 1 - How we work

a.	**Trello (Kanban) Board**. Rather than having a GUI/Testing/Back-end split between people, we will work from a board with "User Stories". These stories should be short examples of what functionality we want added.  For example "I want to be able to display an empty chess board on the GUI".  User stories can be added at meetings, when a bug is found, or late at night as something comes to you in a dream. 

b.	**Stand up meetings**. We agree that Wednesday is our weekly work day and we will make every effort to attend every scheduled meeting.  Missing two or more informal meetings will be raised to the tutor.  We also agree to set aside time every wednesday to contibute and be available to ask and answer questions and help each other.

c.	**Records of Decisions**. Each stand up meeting should follow with a record of decisions made and actions required allocated to people with deadlines.
    
d.	**Code Review**.  Before merging code, someone else in the group must read over, test and run the code.  This stops "But it worked on my machine!" and OS dependent errors (think Windows `\` vs Unix `/` filepaths).

e.	**IDE**.  We all agree to use Intellij IDE to avoid having multiple Class/XML/info files from each setup.
    
### 2 - Where we work

a.	**Discord**: server https://discord.gg/TRHw9awS
   
b.	**Google Drive**. To keep a single point of truth, group documents should be saved and editied _exclusively_ on the group google drive.  This avoids someone missing a meeting and not having any minutes or the computer with the presentation dying last minute.  Always upload everything to the drive.

c.	**Version Control**. Git takes the effort out of merging multiple versions of software and testing out changes, but only if used correctly.  A few rules:

	1)	The root branch is called `master` (or `main`) it is the "production" code and should not be changed directly
	
	2)	Creating a new branch creates a copy of the `master` and is a safe space to try out anything you like, it can always be deleted safely!
	
	3)	If you make something you want to keep, you merge it from that branch into the `master`, this means that if someone has also added changes, you don't overwrite theirs.
	
	4)	Branches that are left out of the `master` branch for a long time go stale and are horrible to merge.  Merge little and often!
	
	5)	When you commit, only add `.java` files (not `.class`) to keep the code machine agnostic.
 
### 3 - What we work on.

a.	**Game Choice**: Bamboo

b.	**Java**: Version 11/15? 

c.	**Build Manager**: Gradle 7.0.1

d.	**GUI**: JavaFX

e.	**Testing**: JUnit 5 (Jupiter)
    
###### Agreed to by the undersigned: ###### 
// Add your name here
Giacomo Terragni



