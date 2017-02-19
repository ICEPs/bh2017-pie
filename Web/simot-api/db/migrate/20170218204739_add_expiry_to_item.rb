class AddExpiryToItem < ActiveRecord::Migration[5.0]
  def change
    change_table :items do |t|
      t.timestamp :expiry_date
    end
  end
end
