CREATE TABLE IF NOT EXISTS channel
(
    channel_id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    name                 VARCHAR(64) NOT NULL,
    created_date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS campaign
(
    campaign_id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    campaign_name         VARCHAR(64) NOT NULL,
    campaign_description  VARCHAR(64) NOT NULL,
    campaign_status       VARCHAR(64) NOT NULL,
    campaign_budget       DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS campaign_channel
(
    campaign_channel_id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    campaign_id                   INTEGER,
    channel_id                    INTEGER,
    foreign key (campaign_id) references campaign(campaign_id),
    foreign key (channel_id) references channel(channel_id)
);

MERGE INTO channel (name) KEY(name) VALUES
   ('TV'),
   ('Social Media'),
   ('Magazine'),
   ('Search Engine');

MERGE INTO campaign(campaign_name, campaign_description, campaign_status, campaign_budget) KEY(campaign_name) VALUES
    ( 'Sale', 'New Sale', 'Active', 1500.00),
    ( 'Clearance', 'New Clearance', 'Active', 1500.00),
    ( 'Promo', 'New Promo', 'Active', 1500.00);

INSERT INTO campaign_channel(campaign_id, channel_id) VALUES
    ( 1, 1),
    ( 2, 1),
    ( 3, 1),
    ( 3, 2),
    ( 3, 3);