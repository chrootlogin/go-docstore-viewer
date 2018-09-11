CREATE TABLE IF NOT EXISTS config (
  ckey TEXT NOT NULL PRIMARY KEY,
  cvalue TEXT NOT NULL
);

INSERT INTO config (ckey, cvalue) VALUES ('db.version', '1');

CREATE TABLE IF NOT EXISTS server_connections (
  id INTEGER NOT NULL PRIMARY KEY,
  url TEXT NOT NULL,
  username TEXT NOT NULL,
  password TEXT NOT NULL
);