# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20170219004938) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "donations", force: :cascade do |t|
    t.string   "message"
    t.string   "organization_name"
    t.integer  "urgency"
    t.datetime "created_at",        null: false
    t.datetime "updated_at",        null: false
    t.integer  "post_author_id"
    t.string   "donation_title"
    t.index ["post_author_id"], name: "index_donations_on_post_author_id", using: :btree
  end

  create_table "items", force: :cascade do |t|
    t.string   "item_name"
    t.string   "item_description"
    t.string   "company_name"
    t.integer  "urgency"
    t.datetime "created_at",       null: false
    t.datetime "updated_at",       null: false
    t.datetime "expiry_date"
    t.string   "donation_title"
    t.integer  "post_author_id"
    t.index ["post_author_id"], name: "index_items_on_post_author_id", using: :btree
  end

  create_table "transactions", force: :cascade do |t|
    t.integer  "item_id"
    t.integer  "donation_id"
    t.integer  "benefactor_id"
    t.integer  "beneficiary_id"
    t.datetime "created_at",     null: false
    t.datetime "updated_at",     null: false
    t.string   "status"
    t.index ["benefactor_id"], name: "index_transactions_on_benefactor_id", using: :btree
    t.index ["beneficiary_id"], name: "index_transactions_on_beneficiary_id", using: :btree
    t.index ["donation_id"], name: "index_transactions_on_donation_id", using: :btree
    t.index ["item_id"], name: "index_transactions_on_item_id", using: :btree
  end

  create_table "users", force: :cascade do |t|
    t.string   "company_name"
    t.string   "email"
    t.string   "password_digest"
    t.string   "address"
    t.string   "rep_name"
    t.string   "rep_contact_info"
    t.string   "company_description"
    t.string   "user_type"
    t.datetime "created_at",          null: false
    t.datetime "updated_at",          null: false
  end

  add_foreign_key "donations", "users", column: "post_author_id"
  add_foreign_key "items", "users", column: "post_author_id"
  add_foreign_key "transactions", "donations"
  add_foreign_key "transactions", "items"
  add_foreign_key "transactions", "users", column: "benefactor_id"
  add_foreign_key "transactions", "users", column: "beneficiary_id"
end
