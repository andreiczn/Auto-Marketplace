[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/YmUJH1TE)
# Sistem pentru gestionarea unui parc auto
### Andrei Căzan

## Descriere
Aplicație destinată gestionării unui parc auto de mașini second-hand. Se urmăresc informații precum stocul de mașini disponibile, istoricul vânzărilor și al achizițiilor efectuate 
și detaliile specifice autovehiculelor si ale clienților.


## Funcționalitățile aplicației

* Administrare stoc autovehicule
    - Adăugarea, actualizarea și ștergerea autovehiculelor din stocul disponibil, în funcție de acțiunile de vânzare/cumpărare efectuate.
* Căutare eficientă
   - Utilizatorul are posibilitatea de a aplica filtre căutării, în funcție de atributele autovehicului precum marca, modelul, un număr maxim de kilomeri parcurși, an de fabricație și un prag superior de preț
* Istoric
   - Fiecare acțiune de vânzare/cumpărare va fi înregistrată, conținând date despre autovehiculul achiziționat/vândut
     și client
* Autentificare
  - Autentificarea utilizatorilor pentru a le permite să modifice stocul afișat 
    (vânzător/administrator), sau de a vizualiza stocul (utilizator normal)
## Arhitectura

![App UML]((https://github.com/Programare-III-2023-2024/p3-proiect-sg1-andreiczn/blob/main/ClassDiagramParcAuto.png))

*Pentru a păstra claritatea diagramei, nu au mai fost incluse următoarele
- Clasele corespunzătoare testelor unitare, AutoFrameTest.java și IstoricFrameTest.java
- Clasa Main.java, rolul ei fiind doar acela de a invoca MainFrame.java - frame-ul principal al aplicației
- Cele 2 fișiere text pe care aplicația le folosește pentru a stoca informațiile, stoc.txt și istoric.txt
