UPDATE booking SET notified = false;
ALTER TABLE booking alter column notified set not null ;
