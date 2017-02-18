class CreateUsers < ActiveRecord::Migration[5.0]
  def change
    create_table :users do |t|
      t.string :company_name
      t.string :email
      t.string :password_digest
      t.string :address
      t.string :rep_name
      t.string :rep_contact_info
      t.string :company_description
      t.string :type

      t.timestamps
    end
  end
end
