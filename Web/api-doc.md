## User Endpoints

GET /:id
POST /:id
- Parameters
  - company name
  - password
  - address
  - representative_name
  - representative_contact_info
  - company_description
  - type

## Donations Endpoints

GET /donations/:donation_id
- Returns a donation object
- Donation Object:
  - Parameters:
    id
    message (string)
    organization_name (string)
    urgency (int)

GET /donations
- Returns list of donation objects

POST /donations
- Parameters:
  message (string)
  organization_name (string)
  urgency (int)

DELETE /donations/:donation_id

## Items Endpoints

GET /items
  - Returns
    item_id
    item_name
    item_description
    company_name
    urgency (int)

GET /items/:id
  - Returns:
    item_id
    item_name
    item_description
    company_name
    urgency (int)

POST /items
  - Parameters
    item_name
    item_description
    company_name
    urgency (int)

## Transaction Endpoints

GET /transactions
  - Returns many
    item_id
    donation_id
    organization_id
    company_id

GET /transactions/:id
  - Returns
    item_id
    donation_id
    organization_id
    company_id

POST /transactions
  - Parameters
    item_id
    donation_id
    organization_id
    company_id
  - Returns
    item_id
    donation_id
    organization_id
    company_id

## Authentication Endpoints

POST /:id
- Parameters
  - company name
  - password
  - address
  - representative_name
  - representative_contact_info
  - company_description
  - type
