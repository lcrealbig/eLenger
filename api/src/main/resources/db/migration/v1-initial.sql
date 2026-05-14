CREATE TABLE identity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    provider VARCHAR(50) NOT NULL,
    provider_id VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE app_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    identity_id UUID NOT NULL UNIQUE REFERENCES identity(id) ON DELETE CASCADE,
    username VARCHAR(100) NOT NULL UNIQUE,
    display_name VARCHAR(255),
    avatar_url VARCHAR(512),
    total_points INT NOT NULL DEFAULT 0,
    confirmation_token TEXT,
    confirmation_token_expiration TIMESTAMP,
    enabled BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE contact (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    owner_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    contact_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',  -- PENDING, ACCEPTED, BLOCKED
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT uq_contact UNIQUE (owner_id, contact_id),
    CONSTRAINT no_self_contact CHECK (owner_id <> contact_id)
);

CREATE TABLE rivalry_group (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    owner_id UUID NOT NULL REFERENCES app_user(id),
    is_public BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE group_member (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    group_id UUID NOT NULL REFERENCES rivalry_group(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    role VARCHAR(50) NOT NULL DEFAULT 'MEMBER',   -- OWNER, ADMIN, MEMBER
    points INT NOT NULL DEFAULT 0,
    joined_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT uq_group_member UNIQUE (group_id, user_id)
);

CREATE TABLE event (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    description TEXT,
    location VARCHAR(255),
    event_date TIMESTAMP,
    owner_id UUID NOT NULL REFERENCES app_user(id),
    group_id UUID REFERENCES rivalry_group(id) ON DELETE SET NULL,
    is_public BOOLEAN NOT NULL DEFAULT true,
    status VARCHAR(50) NOT NULL DEFAULT 'UPCOMING',  -- UPCOMING, ACTIVE, FINISHED, CANCELLED
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE event_participant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    event_id UUID NOT NULL REFERENCES event(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    score INT,
    rank INT,
    joined_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT uq_event_participant UNIQUE (event_id, user_id)
);

CREATE TABLE rivalry (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,   -- DUEL (1v1), TEAM, GROUP_RANKING
    group_id UUID REFERENCES rivalry_group(id) ON DELETE SET NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    started_at TIMESTAMP NOT NULL DEFAULT now(),
    ended_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE rivalry_participant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    rivalry_id UUID NOT NULL REFERENCES rivalry(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES app_user(id) ON DELETE CASCADE,
    team_name VARCHAR(100),
    score INT NOT NULL DEFAULT 0,
    rank INT,
    CONSTRAINT uq_rivalry_participant UNIQUE (rivalry_id, user_id)
);

CREATE TABLE expiration (
    id                      UUID                        NOT NULL,
    owner_type              VARCHAR(50)                 NOT NULL,
    owner_id                VARCHAR(255)                NOT NULL,
    expiration_in_seconds   BIGINT                      NOT NULL,
    description             VARCHAR(255),
    created_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    expiration_at           TIMESTAMP WITHOUT TIME ZONE,

    CONSTRAINT pk_expiration PRIMARY KEY (id)
);

//Indexes
CREATE INDEX idx_expiration_owner ON expiration(owner_type, owner_id);
CREATE INDEX idx_expiration_at ON expiration(expiration_at);