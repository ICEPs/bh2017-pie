class Transaction < ApplicationRecord
  belongs_to :item
  belongs_to :donation
  belongs_to :user, :foreign_key => 'benefactor_id'
  belongs_to :user, :foreign_key => 'beneficiary_id'
end
