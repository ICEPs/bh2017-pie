class AddNameToDonation < ActiveRecord::Migration[5.0]
  def change
    change_table :items do |t|
      t.string :donation_title
    end
  end
end
