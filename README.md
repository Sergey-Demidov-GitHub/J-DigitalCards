## Disclaimer
I started writing this program while still learning japanese to create my own customised tool for studying. Although I 
tried to double check everything very carefully, I cannot fully guarantee that nothing slipped my rigorous inspection. 
The database provided together with the application has 14 decks. Those flash cards are based on the text books 
I used at the time (*GENKI: An Integrated Course in Elementary Japanese(second edition) volume 1&2*).

Also, keep in mind that this program is still at a relatively early stage (from a technical stand point), hence if you 
really try to break it, you will probably succeed. Extensive module testing has yet to be done.

## General Overview
J-DigitalCards is a desktop application for learning japanese vocabulary. It allows the user to create flash cards and
manage them in decks. Every card has a global score to keep track during the training process. In addition to the UI 
convenient short keys are provided to improve card navigation. Using shuffling, filtering as well as
the settings options, the training experience can be customized to fit the users needs.

## User Manual
There are 3 functional pages, which will be introduced in the following paragraphs:
- *Choose deck page*
- *Edit deck page*
- *Train deck page*

### Choose deck page
<details> <summary> show more</summary>

This page shows all decks that are available in the Cards.sqlite database as well as their card count.
It provides the following functionality:

Edit deck name:     Double clicking on a list entry (confirm with enter).  
Edit deck content:  Select deck and click *Edit*.  
Train a deck:       Select deck and click *Train*.  
Create a new deck:  Click *New*.  
Delete a deck:      Select deck and click *Delete* (confirm popup). 
</details>

### Edit deck page
<details> <summary> show more</summary>   

#### Layout
This page can be devided in a top and a bottom part. 
The upper part has a table showing information about the decks content. 
Every row corresponds to a flash card. 
The columns structure those cards in word type, content and score.
The score is split into an overall percentage *(%)* and a streak counter *($)*.
While the first one calculates how much percent of all tries were correct, 
the second one accumulates the amount of either wrong (negative number) or correct (positive number) 
answers in a sequence. It resets after the sequence is broken. 
Since you might want to train translating from japanese to english as well as the other way around, 
there are separate scores for both options. They can be switched by clicking on the button above the score.

The bottom part of this page is for editing a specific flash card, creating a new one or putting in an existing 
one from another deck. 

#### Create card
The card creation process is simple and fast. The user fills out a small intuitive form which 
provides the program with information on how to handle that specific card in the different work stages. 
A flash card is divided into a japanese part and an english part. Each of them have two text areas. 
While the upper one of them cannot be skipped the lower one is optional. To have the best experience I recommend 
following the established conventions. If in doubt you can use the provided decks as an example. 

*General convention:  
Japanese upper text area: kanji-representation of a word (kana-representation if none available)  
Japanese lower text area: kana-representation of a word (empty if only kana-representation available)  
English upper text area: translation  
English lower text area: additional information like particles*  

Currently, there are 4 word types:  
NOUN  
VERB  
ADJECTIVE  
OTHER  

Depending on the classification the card gets a set of attributes assigned to them. 
While NOUN and OTHER have no special features associated with them, for VERB and ADJECTIVE other forms can be generated. 
For now this feature is only available for VERBs:  
During the creation or when editing a verb card a subtype(UNKNOWN, IRREGULAR, U, RU) can be selected. Each of them represents 
a grammar rule set for generating the conjugated forms. If you want to take advantage of this feature, the japanese side 
of the flash card should contain the verb in plain (dictionary) form and nothing else. Any commentary on that side would 
hinder the algorithm from working correctly. If you don't want to use automatic conjugation, simply use the UNKNOWN-subtype. 
In that case the card will be displayed as is. 

After finishing a card to your liking confirm with *OK*.

#### Edit card
If you want to change a card's content or word type, double-click on the corresponding row in the upper table. 
The specifications of that card will be filled into the card creation form. 
From there you can change it and confirm by pressing *OK*.

#### Delete card
Click once on a row in the upper table and then on *Delete*. 
After this it will be removed from this deck. 
If this deck is the only one containing that card it will be deleted permanently.
(Currently there is no popup, deletion is instant.)

#### Adding a card from another deck
Apart from the card creation/editing-form the lower part of this page has also another tab. 
The *Decks* tab can be used to open and browse a different deck. 
Select a deck from the dropdown menu to load it into the table to the right.
To add a single flash card to the upper table, double-click on a row in the lower table.
To add multiple cards at once select multiple rows (using *Ctrl*) and press *Add*.  


Important note:  
When adding a card using this method, this card will be shared between those decks. 
It is still the same card, which means that any changes to the content and score will be shared as well.
Because cards are unique, would be duplicates are ignored. 
The idea behind this is, to have a global score for every card no matter in which context it is trained. 
Also, if you make a mistake, you do not have to look up all the other occurrences of that card.

#### Saving
You can save changes by either pressing *Save* or selecting the appropriate option after pressing *Back*. 
All changes to the loaded deck are temporary until they are saved. 
If you make a mistake that is difficult to correct you can just press *Back* without saving. 
</details>

### Train deck page
<details> <summary> show more</summary>

#### Layout
This page is for training flash cards. 
The card is displayed in the center. While the left card side is always visible, the right one can be hidden.
Under the cards is a navigation block to browse the deck:  
*<--* : Go to previous card.   
*-->* : Go to next card.   
*correct* : Count as correct and go to next card.  
*wrong* : Count as wrong and go to next card.  
*flip* : Show right card side if hidden.   

The button strip on the left provides additional functionality:  
*Default* : Restores the default card order, removes filter (if applied).  
*Shuffle* : Shuffles the deck, creates new card order.  
*Reverse* : Switches left and right card sides until pressed again.  
*Lock* : Makes right card side permanently visible until pressed again.  
*Settings* : Opens settings window, where you can choose which autogenerated forms you want to train.  

In the bottom left corner is a filter form.
It can be used to filter either by absolute percentage or by streak. 
Naturally, it is also applicable together with shuffling and advanced form generation.  

The box in the upper center displays useful information regarding the 
current training progress as well as the deck name.

#### Training with autogenerated forms
When editing a card, you can choose subtypes for verbs. 
If anything but UNKNOWN is chosen, an algorithm will try to generate conjugated forms for that card. 
Those forms will be visible for said card, when encountered during the training process. 
If you wish to train specific forms, you can use the settings menu to select those. 
In this case all qualified verb cards will be replaced by newly generated ones for every selected form.

#### Key Bindings
For your convenience, most important buttons have key bindings:  
*<--* : left arrow key  
*-->* : right arrow key  
*correct* : upwards arrow key  
*wrong* : downwards arrow key  
*flip* : space bar  
*Shuffle* : S  
*Reverse* : R  
*Lock* : L  

</details>

## Compiling Instructions
This is the run configuration I am using:  
Start class: FakeMain  
VM options: ```--module-path "$MODULE_DIR$\vendor\javafx-sdk-15.0.1\lib" --add-modules  javafx.base,javafx.controls,javafx.fxml```  


## License & Copyright
Copyright (C) 2021 Sergey Demidov

Licensed under the [MIT License](LICENSE).