## Disclaimer
I started writing this program while learning japanese to create my own customised tool for studying. Although I 
tried to double check everything very carefully, I cannot fully guarantee that nothing slipped my rigorous inspection. 
The database provided together with the application has 14 decks with flash cards. Those cards are based on the text books 
I used at the time (*GENKI: An Integrated Course in Elementary Japanese(second edition) volume 1&2*).

Also, keep in mind that this program is still at a relatively early stage (from a technical standpoint). Therefor, if you 
really try to break it, you will probably succeed. Extensive module testing has yet to be done.

## General Overview
J-DigitalCards is a desktop application for learning japanese vocabulary. It allows the user to create flash cards and
manage them in decks. Every card has a global score to keep track during the training process. In addition to the UI, 
convenient short keys are provided to improve card navigation. Using shuffling, filtering, as well as 
settings options, the training experience can be customized to fit user's needs.

## User Manual
There are 3 functional pages, which will be introduced in the following paragraphs:
- *Choose deck page*
- *Edit deck page*
- *Train deck page*

### Choose deck page
<details> <summary> show more</summary>

This page shows all decks that are available in the Cards.sqlite database, as well as their card number.
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
This page is divided into top and bottom parts. 
The upper part shows a table displaying information about the deck's content. 
One row corresponds to one flash card.
The properties of the card are such as the word type, the content, and the score are shown in their own columns.
The score is split into an overall percentage *(%)* and a streak counter *($)*.
The former denotes the percentage of correct tries.
The latter accumulates the number of either wrong (negative number) or correct (positive number) 
answers in a sequence. It is incremented every time as a correct answer is given and is reset as a wrong answer is given. 
Since you might want to train translating from japanese to english or the other way around, 
there are separate scores for these two directions. They can be switched by clicking the button above the score.

The bottom contains instruments which allow you to edit a chosen flash card or create a new one.
Additionally, you can add an existing card from a different deck.

#### Create card
The card creation process is simple and fast. The user fills out a short intuitively clear form, which 
tells the program how to handle that specific card during different work stages. 
A flash card is divided into a japanese part and an english part. Each of them has two text areas. 
While the upper one is obligatory, the lower one is optional. To have the best experience I recommend 
following the conventions described below. If in doubt, you can use the existing decks as an example. 

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

Depending on the word type, the card gets a set of attributes.
While NOUN and OTHER have no additional features, for VERB and ADJECTIVE additional forms can be generated. 
For now, this feature is only available for VERBs:  
During creation or when editing a verb card, a subtype(UNKNOWN, IRREGULAR, U, RU) can be selected. Each of these subtypes has 
a set of grammar rules for generating conjugated forms. If you want to make use of this feature, the japanese side 
of the flash card should contain the verb in plain (dictionary) form and nothing else. Any commentary on that side will 
result in incorrect functioning of the algorithm. If you don't want to use automatic conjugation, simply use the UNKNOWN-subtype. 
In that case, the card will be displayed as is. 

After all the information is entered, confirm with *OK*.

#### Edit card
If you want to change the content of a card or the word type, double-click on the corresponding row in the upper table. 
All content of that card will be displayed in the card creation form. 
There you can change it and confirm by pressing *OK*.

#### Delete card
Click once on a row in the upper table and then on *Delete*. 
After this, it will be removed from this deck. 
If this deck is the only one containing that card, it will be deleted permanently.

#### Adding a card from another deck
In addition to the card creation/editing-form, there is one more tab in the bottom part of the page. 
The *Decks* tab can be used to open and browse a different deck. 
Select a deck from the dropdown menu to load it into the table to the right.
To add a single flash card to the upper table, double-click on a row in the lower table.
To add several cards at once, select multiple rows (using *Ctrl*) and press *Add*.  


Important note:  
When adding a card using this method, this card will be shared between several decks. 
It is still the same card, which means that any changes to the content and the score will be shared, as well.
Because cards are unique, duplicates will be ignored. 
The idea behind this is to have a global score for every card, no matter in which context it is trained. 
Also, if you make a mistake, you do not have to look up all the other occurrences of that card.

#### Saving
You can save changes by either pressing *Save* or selecting the corresponding option after pressing *Back*. 
Changes to the loaded deck do not take effect until saved. 
If you make a mistake that is difficult to correct you can just press *Back* without saving. 
</details>

### Train deck page
<details> <summary> show more</summary>

#### Layout
This page is for training flash cards. 
The card is displayed in the center. While the left side of the card is always visible, the right one can be hidden.
Below the card, there is a navigation block to browse the deck:  
*<--* : Go to the previous card.   
*-->* : Go to the next card.   
*correct* : Mark as correct and go to the next card.  
*wrong* : Mark as wrong and go to the next card.  
*flip* : Show the right side of the card (if hidden).   

The buttons on the left provide additional functionality:  
*Default* : Restores the default card order, removes the filter (if applied).  
*Shuffle* : Shuffles the deck, creates new card order.  
*Reverse* : Switches left and right sides of the card.  
*Lock* : Makes the right side of the card permanently visible.  
*Settings* : Opens settings window, where you can choose, which autogenerated forms you want to train.  

In the bottom left corner, there is a filter form.
It can be used to filter either by absolute percentage or by streak. 
It can be used together with shuffling and advanced form generation.  

The box in the upper center displays useful information regarding the 
current training progress as well as the deck name.

#### Training with autogenerated forms
When editing a card, you can choose subtypes for verbs. 
If anything but UNKNOWN is chosen, an algorithm will try to generate conjugated forms for that card. 
Those forms will be visible for that card, when it is encountered during the training process. 
If you wish to train specific forms, you can use the settings menu to select those. 
In this case, all qualified verb cards will be replaced by newly generated ones for every selected form.

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