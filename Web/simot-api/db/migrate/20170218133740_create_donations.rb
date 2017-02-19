class CreateDonations < ActiveRecord::Migration[5.0]
  def change
    create_table :donations do |t|
      t.string :message
      t.string :organization_name
      t.integer :urgency

      t.timestamps
    end
  end
end
