# KWICArchitecture
Implementation of the KWIC application in multiple architectures

# Overview of Application
The purpose of KWIC is to find the keywords in various input lines, find their context, and then stort them alphabetically. Each of these keywords is used as the key to be sorted alphabetically.

# How to run
	javac KWICApp.java #compiles all of the files
	java KWICApp -f inputfile.txt #runs the KWICApp with an input file (-f) and no stop words
	java KWICApp -f inputfile.txt -s stopwords.txt #runs the KWICApp with an input file (-f) and stop words file (-s)

# Basic Use
Example input:
	The Phantom Menace
	The Clone Wars
	The Revenge of the Sith
	A New Hope
	The Empire Strikes Back
	Return of the Jedi
	The Force Awakens

The expected output is:
>	Key				Value

>	-------------------------------------------------

>	A				New Hope 

>	Awakens				The Force 

>	Back				The Empire Strikes 

>	Clone				Wars The 

>	Empire				Strikes Back The 

>	Force				Awakens The

>	Hope				A New 

>	Jedi				Return of the 

>	Menace				The Phantom 

>	New				Hope A 

>	of				the Sith The Revenge 

>	of				the Jedi Return 

>	Phantom				Menace The 

>	Return				of the Jedi 

>	Revenge				of the Sith The 

>	Sith				The Revenge of the 

>	Strikes				Back The Empire 

>	The				Phantom Menace 

>	The				Clone Wars 

>	The				Revenge of the Sith 

>	the				Sith The Revenge of 

>	The				Empire Strikes Back 

>	the				Jedi Return of 

>	The				Force Awakens 

>	Wars				The Clone 

The key is the keyword that is sort alphabetically, and then the value is the rest of the sentence that may be circularly shifted. For example, if the keyword occurs in the middle of the sentence, the value will start with the continuation of the sentence, and then wrap around to the words in the sentence that occurred before the keyword.

# Optional Use - Stopwords
Users can also specify a list of stopwords (keywords that should be skipped).

Example input:
        The Phantom Menace
        The Clone Wars
        The Revenge of the Sith
        A New Hope
        The Empire Strikes Back
        Return of the Jedi
        The Force Awakens

Stopwords:
	A
	Of
	The

Expected Output:
	Key                             Value
        -------------------------------------------------
        Awakens                         The Force
        Back                            The Empire Strikes
        Clone                           Wars The
        Empire                          Strikes Back The
        Force                           Awakens The
        Hope                            A New
        Jedi                            Return of the
        Menace                          The Phantom
        New                             Hope A
        Phantom                         Menace The
        Return                          of the Jedi
        Revenge                         of the Sith The
        Sith                            The Revenge of the
        Strikes                         Back The Empire
        Wars                            The Clone
