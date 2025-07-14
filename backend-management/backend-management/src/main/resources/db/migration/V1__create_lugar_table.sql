-- ====================================================
-- 1) Criação das tabelas
-- ====================================================

-- Tabela de categorias
CREATE TABLE categorias (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL UNIQUE,
  descricao TEXT NOT NULL
);

-- Tabela de lugares
CREATE TABLE lugares (	
  id SERIAL PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  categoria_id INTEGER NOT NULL
    REFERENCES categorias(id)
    ON DELETE CASCADE,
  localizacao VARCHAR(100),
  url_foto TEXT,
  avaliacao SMALLINT CHECK (avaliacao BETWEEN 1 AND 5)
);


INSERT INTO categorias (nome, descricao) VALUES
  ('Shoppings',          'Lugares para fazer compras e passear'),
  ('Praias',             'Lugares para tomar banho com a família'),
  ('Bares',              'Estabelecimentos para beber e socializar'),
  ('Parques',            'Espaços verdes para lazer e atividades ao ar livre'),
  ('Postos de Combustível', 'Locais para abastecer veículos');



INSERT INTO lugares (nome, categoria_id, localizacao, url_foto, avaliacao) VALUES
  -- Praias
  ('Praia do Zé',           2, 'Angra dos Reis, RJ',
    'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX6BKTqW4FCh278BcBxivD1kwAUfdxDxzqRw&s', 4),
  ('Praia do Espelho',      2, 'Trancoso, BA',
    'https://www.melhoresdestinos.com.br/wp-content/uploads/2019/01/melhores-praias-do-brasil-praia-do-espelho-820x547.jpg', 5),
  ('Praia de Copacabana',   2, 'Rio de Janeiro, RJ',
    'https://upload.wikimedia.org/wikipedia/commons/6/6b/Copacabana_beach_riricc.jpg', 5),

  -- Shoppings
  ('Shopping Iguatemi SP',  1, 'São Paulo, SP',
    'https://cdn.pixabay.com/photo/2014/09/08/17/08/not-439331_1280.jpg', 4),
  ('Shopping Recife',       1, 'Recife, PE',
    'https://cdn.pixabay.com/photo/2014/09/08/17/08/not-439331_1280.jpg', 4),

  -- Bares
  ('Bar Brahma',            3, 'São Paulo, SP',
    'https://cdn.pixabay.com/photo/2014/09/08/17/08/not-439331_1280.jpg', 4),
  ('Bar Astor',             3, 'São Paulo, SP',
    'https://cdn.pixabay.com/photo/2014/09/08/17/08/not-439331_1280.jpg', 5),

  -- Parques
  ('Parque Ibirapuera',     4, 'São Paulo, SP',
    'https://cdn.pixabay.com/photo/2014/09/08/17/08/not-439331_1280.jpg', 4),
  ('Parque da Cidade',      4, 'Brasília, DF',
    'https://cdn.pixabay.com/photo/2014/09/08/17/08/not-439331_1280.jpg', 4),

  -- Postos de Combustível
  ('Posto Ipiranga',        5, 'Rio de Janeiro, RJ',
    'https://cdn.pixabay.com/photo/2014/09/08/17/08/not-439331_1280.jpg', 4),
  ('Posto Shell Avenida',   5, 'Salvador, BA',
    'https://cdn.pixabay.com/photo/2014/09/08/17/08/not-439331_1280.jpg', 3);

