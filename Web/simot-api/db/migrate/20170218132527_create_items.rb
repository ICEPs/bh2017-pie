class CreateItems < ActiveRecord::Migration[5.0]
  def change
    create_table :items do |t|
      t.string :item_name
      t.string :item_description
      t.string :company_name
      t.integer :urgency

      t.timestamps
    end
  end
end
