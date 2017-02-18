class AddUserToDonation < ActiveRecord::Migration[5.0]
  def change
    change_table :donations do |t|
      t.references :post_author, foreign_key: {to_table: :users}
    end
  end
end
