Progetto "ELementi di ingegneria del software" - Ingegneria informatica (triennale)
--------------
Supponete di essere in ambiente Java Micro Edition, precisamente CLDC1.1
(https://docs.oracle.com/javame/config/cldc/ref-impl/cldc1.1/jsr139/index.html)
<br>
Supponete di voler utilizzare in questo ambiente una libreria di classi (myLib) nata in ambiente J2SE 1.4.2
(http://geas.dei.unipd.it/jdk1.4.2/docs/api/
https://www2.cs.duke.edu/csed/java/jdk1.4.2/docs/api/index.html).
<br>
In particolare, la libreria contiene classi che fanno uso dell’interfaccia List, e quindi di quelle ad essa
connesse (Collection, Iterator, ListIterator) del Java2 Collections Framework versione 1.4.2.
Sviluppate l’adapter per l’interfaccia List utilizzando come adaptee la classe Vector di CLDC 1.1.
<br>
Lavorate in ambiente Java versione corrente, ma ricordate che e’ FONDAMENTALE che il vostro codice
utilizzi solo le funzionalita’ presenti in CLDC 1.1 per realizzare l’adapter.
<br>
Per evitare collisioni con le interfacce List, Iterator e ListIterator della versione corrente di Java dovete
definire localmente al package del vostro adapter (package che dovete chiamare myAdapter senza ulteriori
livelli di nidificazione) le interfacce HList, HCollection, HIterator ed HListIterator con tutti i metodi delle
interfacce List, Collection, Iterator e ListIterator della versione 1.4.2 di Java. La vostra classe adapter deve
chiamarsi ListAdaper, deve appartenere al package myAdapter e deve implementare le interfacce HList,
HCollection.
<br>
Il comportamento dei vostri adapters e dei loro metodi deve essere esattamente quello descritto dalla
documentazione di J2SE 1.4.2 e devono essere implementate tutte le optional operations.
<br>
Devono essere compliant con la documentazione della versione 1.4.2 anche gli iteratori e devono essere
implementate anche tutte le optional operations degli iteratori stessi. La o le classi che implementano gli
iteratori devono far parte del package myAdapter e deve/devono implementare le interfacce HIterator e
HListIterator.
<br>
Dovete utilizzare la metodologia Test Driven Development, e, quindi, definire ed Implementare le test suite
Junit per le classi sviluppate. Le classi di test devono essere contenute in un package myTest (senza ulteriori
livelli di nidificazione). Il package deve contenere una classe TestRunner che possa essere invocata da linea
di comando, eseguire tutti i test da voi definiti, fornire il risultato dei test ed il numero complessivo di test
eseguiti.
<br>
Documentate la/le vostra test suite utilizzando il template “SAFe” descritto nella tabella 1 di questo
documento.
<br>
Documentate ogni test case secondo il template “Homework” descritto nella tabella 2 di questo
documento.
<br>
E’ possibile fornire la documentazione in formato pdf o in formato javadoc, in entrambi i casi le diverse voci
per i diversi test devono essere facilmente leggibili e distinguibili dalle altre. La documentazione deve
essere presente e deve essere contenuta in una cartella Documentazione separata da quella dei sorgenti.
<br>
Utilizzate il framework Junit nella versione usata a lezione. Dovete comunque dichiarare nella
documentazione la versione utilizzata, le componenti del framework utilizzate e le eventuali librerie di
matcher utilizzate. Se utilizzerete librerie matcher, esse devono essere fornite nella consegna in formato
jar, in una cartella dedicata denominata Matcher.
<br>
Scrivete la documentazione delle classi (utilizzate il tool javadoc) fornendo almeno la descrizione delle classi
e la documentazione di base (paragrafi parameters, returns, throws) dei metodi. Non vi e’ impedito l’uso di
annotazioni avanzate, ma non e’ obbligatorio.
