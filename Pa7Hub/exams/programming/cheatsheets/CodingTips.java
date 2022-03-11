package exams.programming.cheatsheets;

/**
 * Einfach mal generell ein paar Coding Tips. Hab zwischendurch oft mal so ein paar Dinge gesehen, die man auch einfacher/besser machen kann.
 * Coden hat sehr viel mit preference zu tun, der eine mag Lösung A mehr, der andere B. Manche Dinge sind aber einfach
 * besser, wenn man sie auf bestimmte Art und Weise macht.
 * Ich versuche das mit ein paar Beispielen zu erklären, die mir so beim coden selbst aufgefallen sind.
 */
public class CodingTips {
    /* Dynamisches Coden

        Bsp.: Man hat in JavaScript einen Docker, der hält genau drei Zeilen Inhalt à 88px Höhe.
        Jetzt könnte man natürlich einfach
            Height: 264px
        für den Docker schreiben. Dann lässt man das alles so liegen für drei Monate und guckt sich den Code dann nochmal an.
        Ich wette 10€, dass das niemand mehr verstehen wird. Viel besser wäre daher sowas wie:

            const ROW_HEIGHT = 88px // Festlegung der exakten Row Height
            const NBR_OF_ROWS = arrayThatHoldsAllRows.length() // Festlegung der exakten Anzahl an Rows im Docker
            ...
            ...
            Height: ROW_HEIGHT*NBR_OF_ROWS

        Wenn man sich den Code jetzt anguckt sieht man immer sofort, dass jede Row eine exakte Höhe hat und der Docker das dynamisch berechnet.
        Fügen wir also eine Row hinzu, müssen wir nix neu berechnen.
        Dynamisches Coden wird mit steigender komplexität des Projekts immer schwieriger. Was, wenn die Rows bspw. nicht in einem Array sind,
        sondern alle aus irgendwelchen Ecken generated werden. Man sollte aber immer darauf abzielen, den Code so dynamisch wie möglich zu gestalten.
        AKA "avoid hardcoding"
     */

    /* Kommentieren

        Wenn man mit dem Coden fertig ist, sollte man am besten nochmal drüber gehen und alles kommentieren, was einem nicht instantly klar sein könnte.
        Das ist nicht nur für andere gut, sondern auch, wenn man das Projekt für ein paar Monate liegen lässt und dann wieder kommt.
        Wenn man tests definiert (was man bei mir bspw. selten sieht) dann kann man ein 'expected output' dazu schreiben, sodass falls es mal kaputt geht,
        man sofort sehen kann, was und wieso da (etwas) erwartet wird.
        Kommentare machen das verstehen einfacher.

        Ich musste für meine Praxisarbeit einen ganzen Block von 200 Zeilen Passwortcheck verstehen, ohne Kommentare. Und die einzige Hilfe von anderen war
        "Joa kriegste schon hin das zu verstehen". Dazu war das noch von 2017 also richtig deprecated und hat auch nur so halb funktioniert. Cool shit.
     */

    /* Benennung von Variablen

        Also neben dem obvious "dont call your variable tbvnach because its the best variable name anyone can have" gibt es auch einige Tricks, wie man sich Dinge leichter merken kann.
        Bspw. nenne ich alle meine in Klassen definierten lokale Variablen immer "_NAME", sodass ich immer weiß, dass die über "_" erreichbar sind.
        Ich kenne bspw. auch, dass man Variablen mit einem "+" oder "-" beginnt, um zu zeigen, dass die private oder public sind. Das kann man alles machen.
     */

    /* Sprache

        Ich weiß ja nicht wie es bei euch in den Firmen ist, aber wir coden immer auf Englisch. Ist auch wieder preference, aber es ist eigentlich gut zu lernen,
        Variablen, Erklärungen und alles weitere im Code auf Englisch zu schreiben. Auf deutsch geht das natürlich auch, aber kann im zweifelsfall komisch werden.
        Ist aber komplette personal preference.
     */

    /* Commit messages

        Das hängt sehr viel davon ab, wie das im Unternehmen gemacht wird.
        Normalerweise benutzt man beim committen immer die Imperative Form und versucht es so kurz und beschreibend wie möglich zu halten.
        Bsp.:
            Add more Cheatsheets
            Add general coding tips
            Fix info.txt
            WIP

        Man kann (wenn man möchte) auch kleine Blöcke mit Erklärungen hinzufügen. Das machen wir auf der Arbeit aber eher in Pull Requests, als tatsächlichen Commits, da man
        beim mergen von commits und cherry picking etc. einfach die kurze Version sehen will, ohne sich einen Batzen an Zeilen durchzulesen.

        Imperativ schreibt man, weil man die Commit Message als "If you apply this commit, it will..."
            "Add more Cheatsheets"
            "Add general coding tips"
            ...
        liest.

        (Und am besten klatscht man noch ein WIP drunter damit jeder weiß, es wird nie fertig. :P)
     */

    // TODO vielleicht fallen mir noch mehr Sachen ein

}
