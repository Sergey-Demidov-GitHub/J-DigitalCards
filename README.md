## Disclaimer
I started writing this program while still learning japanese to create my own customised tool for studying. Although I 
tried to double check everything very carefully, I cannot fully guarantee that nothing slipped my rigorous inspection. 
The database provided together with the application has 14 decks. Those flash cards are based on the text books 
I was using at the time (*GENKI: An Integrated Course in Elementary Japanese(second edition) volume 1&2*).

Also, keep in mind that this program is still in a relatively early stage (from a technical stand point), hence if you 
really try to break it, you will probably succeed.

## General Overview
J-DigitalCards is a desktop application for learning japanese vocabulary. It allows the user to create flash cards and
manage them in decks. Every card has a global score to keep track during the training process. In addition to the UI 
convenient short keys are provided to improve card navigation. Using shuffling, filtering as well as
the settings options, the training experience can be customized to fit the users needs.

## Manual
There are 3 functional pages, which will be introduced in the following paragraphs:
- *Choose deck page*
- *Edit deck page*
- *Train deck page*

### Choose deck page
Edit deck name:     Double clicking on a list entry (confirm with enter).  
Edit deck content:  Select deck and click *Edit*.  
Train a deck:       Select deck and click *Train*.  
Create a new deck:  Click *New*.  
Delete a deck:      Select deck and click *Delete* (confirm popup). 


### Edit deck page
#### Card Creation
The card creation process is simple and fast. The user fills out a small intuitive form which 
provides the program with information on how to handle that specific card in the different work stages. 
A flash card is divided into a japanese part and an english part. Each of them have two text areas. 
While the upper one of them cannot be skipped the lower one is optional. To have the best experience I recommend 
following the established conventions. If in doubt you can look up some of provided decks as an example. 

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

### Train deck page

## License & Copyright
Copyright (C) 2021 Sergey Demidov

Licensed under the [MIT License](LICENSE).