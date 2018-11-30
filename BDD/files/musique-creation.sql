CREATE TABLE `eleves` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `instruments` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prix` int(11) NOT NULL,
  `prix_cours` int(11) NOT NULL,
  `location` char(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `professeurs` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `instrument` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;