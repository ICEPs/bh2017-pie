class Transcation < ApplicationRecord
  belongs_to :item_id
  belongs_to :donation_id
end
