CREATE TABLE IF NOT EXISTS channel
(
    channel_id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    name                 VARCHAR(64) NOT NULL UNIQUE,
    created_date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS campaign
(
    campaign_id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    campaign_name         VARCHAR(64) NOT NULL UNIQUE,
    campaign_description  VARCHAR(64) NOT NULL UNIQUE,
    campaign_status       VARCHAR(64) NOT NULL,
    campaign_budget       DOUBLE NOT NULL,
    campaign_start_date   DATE NOT NULL,
    campaign_end_date     DATE NULL
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

MERGE INTO campaign(campaign_name, campaign_description, campaign_status, campaign_budget, campaign_start_date, campaign_end_date) KEY(campaign_name) VALUES
    ( 'Oblivion Anniversary', '20th Year Anniversary Remake', 'Active', 20000.00, '2025-01-01', '2026-01-01'),
    ( 'Skyrim HD Remaster', '15th Anniversary Remaster', 'Cancelled', 30000.00, '2025-01-01', '2027-01-01'),
    ( 'Elder Scrolls VI Teaser', 'Teaser Campaign for Elder Scrolls VI', 'Pending Approval', 100000.00, '2025-01-01', '2028-01-01');

MERGE INTO campaign_channel(campaign_id, channel_id) KEY(campaign_id, channel_id) VALUES
    ( 1, 1),
    ( 2, 1),
    ( 3, 1),
    ( 3, 2),
    ( 3, 3);