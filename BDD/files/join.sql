
SELECT * FROM school.professeurs
		 LEFT OUTER JOIN instruments
		 ON professeurs.instrument = instruments.id;
		 
SELECT (id, nom) FROM school.eleves 
		 JOIN eleves_instruments 
		 ON eleves.nom SET eleves_instruments.id=eleves.id;	

SELECT e.*, i.*
FROM school.eleves e
INNER JOIN school.eleves_instruments ei
ON ei.id = e.id
INNER JOIN school.instruments i
ON i.nom = ei.instrument;
		 